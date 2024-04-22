# ConcertBookingServer

TDD를 기반으로한 콘서트 예약 시스템 입니다.<br><br>
동시 최대 50명만 API를 호출할 수 있는 권한을 갖게 되며, 나머지 인원은 대기열 시스템에의해 관리됩니다.<br><br>
앞선 이용자가 서비스이용이 끝나면, 대기중인 유저들은 순차적으로 권한을 부여받습니다. <br><br>


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


- dev : 신규 기능 개발 및 수정이 진행되는 개발
  
- stg : dev환경에서 개발된 신규기능과 개선사항을 실제 서비스와 유사한 환경에서 테스트.
  
- prd : 서비스 실제 유저에게 제공

  <br>
 
</details>


<details>
<summary>Swagger API Docs</summary>  

![스크린샷 2024-04-18 175910](https://github.com/ggplay149/3rdWeek_Concert_Reservation_Server/assets/142002833/e136979c-20c4-45cf-aa6f-2c14ff242ee1)
 
</details>
