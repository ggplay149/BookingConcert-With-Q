# ConcertBookingServer

 <br>
TDD를 기반으로한 콘서트 예약 시스템 입니다.<br><br>
동시 최대 50명만 API를 호출할 수 있는 권한을 갖게 되며, 나머지 인원은 대기열 시스템에의해 관리됩니다.<br><br>
앞선 이용자가 서비스이용이 끝나면, 대기중인 유저들은 순차적으로 권한을 부여받습니다. <br><br>
 <br>


 <details>
<summary>동시성 이슈 처리</summary>  
 <br> 

<h2>해결 방법 분석</h2>

>DB Lock

- 구현 복잡도: 내장된 락을 사용하거나 ORM을 사용하여, 단편적인 구현이 어렵지 않음
- 효율성 : 일반적 연산에 대한 락을 제공하는데 효과적
- 성능 : 병목 현상을 유발할 수 있으며, 락이 자주 요청되는 경우 성능 저하나 과도한 db부하를 유발
- 장점 : 일관성과  ACID 특성을 유지하기 용이
- 단점 : 병목 현상과 db 성능 저하를 유발, 분산환경에서 구현 어려움

  
<br>

>Redis 분산락:

- 구현복잡도 : 분산 환경에서 락을 구현하기 간단한 편, Redis 자체 라이브러리나 템플릿을 제공
- 효율성 : 인메모리 기반의 빠른 속도
- 성능 : key-value의 원자성을 이용한 락이기 때문에, DB 부하 최소화
- 장점 : 빠른속도, db부하 최소화
- 단점 : 데이터 일관성과 클러스터 구성에 대한 추가 처리가 필요

<br>

>Lettuce
- 구현이 간단한편
- Spin Lock : 많은 스레드가 획득 대기시, 많은 redis 부하

  
<br>

>Redisson
- 추가 의존성 필요
- 락 획득 재시도 기본 제공
- pub-sub 형식의 lock : 적은 redis 부하

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

> 다른 인스턴스(디바이스)에서 충전/사용 동시 요청 순차 처리

- 현재 : DB 락으로 제어
- 개선 : Redisson pub/sub 으로 제어
- 이유 :
1) 최초 요청에 락획득 실패하더라도 순차적으로 다시 락을 획득해야함
2) Redis 부하를 줄이기 위해 Redisson pub/sub 형식 사용
3) DB 부하 최소화

 <br>
 
</details>




<details>
<summary>API 설계</summary>  
 <br>
   
- ERD : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/ERD.md
  
- Sequence diagram : https://github.com/ggplay149/ConcertBookingServer/blob/main/src/main/resources/doc/SequenceDiagram.md

 <br>
 
</details>

<details>
<summary>Git branch 전략</summary>

   <br>

Git hub Flow

- dev : 신규 기능 개발 및 수정 진행
  
- prd : 서비스 실제 유저에게 제공

  <br>
 
</details>


<details>
<summary>Swagger API Docs</summary>  

![스크린샷 2024-04-18 175910](https://github.com/ggplay149/3rdWeek_Concert_Reservation_Server/assets/142002833/e136979c-20c4-45cf-aa6f-2c14ff242ee1)
 
</details>
