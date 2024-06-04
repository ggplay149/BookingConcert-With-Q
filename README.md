# BookingConcert-With-Q

<details>

<summary>API 설계</summary>  
 <br>


   
- ERD : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/ERD.md
  
- Sequence diagram : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/SequenceDiagram.md

- Swagger Docs :
 <br>
 
  ![스크린샷 2024-04-18 175910](https://github.com/ggplay149/3rdWeek_Concert_Reservation_Server/assets/142002833/e136979c-20c4-45cf-aa6f-2c14ff242ee1)

 <br>
 
</details>



<details>
<summary>대기열 구현</summary>  
   
 <br>

 

> 프로세스

1. 대기열을 크게 Active 와 Wait DB로 분기
2. 최초의 대기열 입장 요청시, 유저 Wait DB에 추가
3. 짧은 간격의 스케쥴러가 서비스 이용 최대 정원에 맞춰 순차적으로 Wait 유저를 Active DB로 이동
4. Active 전환시, 본 서비스 이용이 가능
5. 1분간격의 스케쥴러가 모든 Active 유저 유효시간 체크후, 만료시 삭제

   <br>

> 설계

- Active 유저 최대 정원 제한
  
1) 트래픽을 고정적으로 제한하여 높은 안정성
2) 트래픽 처리속도가 느려질수 있지만, 콘서트 예매 특성상 유저별 이용시간이 길지 않음.

   <br>

- Active 상태 유효시간 설정
  
1) 콘서트 예매 특성상, 유저별 이용시간이 길지 않지만, 평균적인 이용시간을 특정하기 어려움
2) 기존 Active 유저가 서비스에 무기한 체류시, 서비스가 멈출 수 있음.

   <br>

- Active 유저 최대정원은 콘서트의 좌석수와 트래픽예상치 비례하여 설정
  
1) 콘서트 규모에 비하여 지나치게 적은 정원제한은 처리속도가 너무 낮아짐.
2) 콘서트 규모에 비하여 지나치게 많은 정원제한은 서버 부하가 높아짐.

<br>

> 구현방식

- Redis 사용
  
1) 짧은 간격의 대기열 조회로 인한 DB부하 최소화
2) 대기열 정보는 휘발성 데이터로 Redis 캐싱을 통해 저장
   
   <br>

- Redis Sorted Set 자료구조 사용
  
1) Set 자료구조의 특성을 사용하여 유저 중복 등록방지 
2) Wait DB에서 순차적인 Active 전환을 위해 Sorted Set의 score값 사용
3) Wait DB에 이미 존재하는 유저가 재요청시, 중복 등록은 방지되고 score 값만 자동갱신
4) Active DB로 전환시 score에 전환시점 시간을 저장하여 active 만료시간 검증시 사용

 
 <br>
 
</details>


 <details>
<summary>동시성 이슈 처리</summary>  
 <br> 
   
> 대기열 시스템

- 트래픽을 받는 redis의 key를 단일로 두지않고 active 와 wait으로 분리
- 모든 대기열 추가 요청 트래픽은 일단 wait으로 추가
- 이후 wait의 쌓인 대기인원을 순차성을 보장(sortedSet score)하여, 조건에 맞춰 active로 전환


> 같은좌석에 대한 임시예약요청

- 임시예약 내역은 DB의 부하를 줄이기 위해 온전히 Redis에서 관리
- 최초의 임시예약 요청 유저가 분산락을 통해 해당 좌석선점
- 결제후 해당 좌석에 대한 분산락 해제
  
<br>


<br>


<h2>UseCase별 분석</h2>

>같은 좌석에대한 동시예약 요청 제어

- 현재 : 예약번호(콘서트날짜 + 콘서트 아이디 + 좌석번호)를 유니크 키 중복방지로 제어
- 개선 : Redis simple lock 으로 제어
- 이유 :
1) DB 부하 최소화
2) 최초의 시도에서 좌석예약에 실패했다면, 임시배정된 좌석에 관하여 다시 lock 획득 시도할 필요가 없음
3) 따라서 구현이 간편한 Redis Lettuce 사용

   
<br>

>임시 배정 유효시간동안 예약 동시 요청 불가능

- 현재 : 짧은 텀의 스케쥴러로, 최초의 생성시간으로부터 5분이 지났는데 최종 결제되지 않은 내역이 있다면 삭제 
- 개선 : Redis ttl로 유효시간 관리
- 이유 :
1) 짧은텀의 스케쥴러 사용과 물리적 delete으로 인한 db 부하 최소화 
2) 실시간성 개선

   
<br>

> 유저포인트 충전/사용 동시 요청 순차 처리

- 현재 : DB 락으로 제어
- 개선 : redis 락으로 순차처리
- 이유 :
1) 최초 요청에 락획득 실패하더라도 순차적으로 다시 락을 획득해야함
2) Redis 부하를 줄이기 위해 Redisson pub/sub 형식 사용
3) DB 부하 최소화

 <br>
 
</details>




<details>

