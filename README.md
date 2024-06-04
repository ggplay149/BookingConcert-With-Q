# BookingConcert-With-Q 
 <br>

:heavy_check_mark: TDD 기반 콘서트 예약 시스템 

:heavy_check_mark: 대규모 트래픽에 안정적인 대기열 구조 사용



  
<br>

<details>

<summary>:clipboard: API 설계 </summary>  
 <br>


   
- ERD : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/ERD.md
  
- Sequence diagram : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/SequenceDiagram.md

- Swagger Docs :
 <br>
 
  ![스크린샷 2024-04-18 175910](https://github.com/ggplay149/3rdWeek_Concert_Reservation_Server/assets/142002833/e136979c-20c4-45cf-aa6f-2c14ff242ee1)

 <br>
 
</details>



<details>
<summary>:inbox_tray: 대기열 구현  </summary>  
   
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
<summary>:family: 동시성 이슈 처리  </summary>  
 <br> 
  

>같은 좌석에대한 동시예약 요청 제어

- 기존 : 예약번호(콘서트날짜 + 콘서트 아이디 + 좌석번호)를 유니크 키 중복방지로 제어
- 개선 : Redis simple lock 으로 제어
- 이유 :
1) DB 부하 최소화
2) 최초의 시도에서 좌석예약에 실패했다면, 임시배정된 좌석에 관하여 다시 lock 획득 시도할 필요가 없음
3) 따라서 구현이 간편한 Redis Lettuce 사용

 <br>

>같은 좌석에대한 동시예약 요청 제어

- 기존 : 예약번호(콘서트날짜 + 콘서트 아이디 + 좌석번호)를 유니크 키 중복방지로 제어
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
<summary>:chart_with_upwards_trend: 트래픽 처리  </summary> 

 <br>

> 대기열 : Redis 캐싱을 통한 DB 대체

- 대기열은 가장 많은 트래픽을 직접 받는 도메인
- Redis를 사용하여 높은 트래픽으로 인한 직접적인 DB 부하 방지
- 높은 휘발성의 대기열 정보를 DB에서 매번 삽입/물리삭제 하는것은 비효율적
- 빠르고 짧은 주기의 Redis 캐싱으로 대체 하여 DB 부하 완전히 제거

  <br>

> Payment : 이벤트 처리를 통한 트랜잭션 범위 최소화

- UseCase의 메인 비지니스 로직 처리 후 파생되는 내부 정보 update를 이벤트처리
- 내부 이벤트 처리 후,  kafka를 사용하여 외부 정보(ex : 유저 활성화 해제,lock 해제, 외부api 호출 등) 를 비동기 처리

  <br>

> Resevation : DB 테이블 쿼리 index 최적화 

- 많은 양의 데이터가 쌓이고, 메인 비지니스 로직을 위해 조회성 요청이 빈번함  
- 높은 Cardinality를 가진 컬럼 "예약번호"가 존재하여, 예약번호를 기준으로 인덱스 설정

  <br>


 
</details>

 <details>
<summary>:runner: 업데이트 예정 </summary> 

<br>

- 외부 이벤트(Kafka) 발행과 소비를 보장하는 OUTBOX 패턴 도입
- 부하 테스트 문서보고
- Webflux 도입
    
</details>
