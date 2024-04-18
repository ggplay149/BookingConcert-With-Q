# API Interface

<h2>유저 토큰 발급 API</h2>

```
[POST]/token/issue
 - Request
{
    user_id 
}
 - Response 
{
     //발급 성공시
    jwt Token
    {
    UUID, token_status, 발행시간, 유효시간
    }
    ,message : "약 #{유효시간}분 동안 예약서비스 이용가능합니다."   
      
    //발급 실패시
    대기순번
    ,message : "#{대기순번}번째 대기순번으로 등록되었습니다. 잠시후 다시 요청해주십시요."
       
}
```

<h2>예약가능 날짜 API</h2>

```
[GET]/concert/availableDate
 - Response 
{
    List<정원이 꽉차지 않은 콘서트명과 날짜> 
}
```

<h2>예약가능 좌석 API</h2>

```
[GET]/concert/availableSeat
 - Request
{
    concert_date, concert_title
}

 - Response 
{
    List<해당 날짜, 해당 콘서트의 잔여 좌석번호>  
}
```


<h2>좌석 예약 요청 API</h2>

```
[POST]/reserve
 - Request
{
    concert_date, concert_id , seat_no
}
   - Response 
{
    //예약 성공 시
    resevere_id,
    message : "좌석이 임시배정(5분) 되었습니다. 결제 후 예약이 확정됩니다."
     
    //예약 실패 시
    message : "임시배정시간이 만료되어 예약에 실패하였습니다."  
              or "잔액부족으로 예약에 실패하였습니다." 
              or "해당 날짜 콘서트의 예약이 마감되었습니다." 
}
```

<h2>잔액 충천/조회 API</h2>

```
//조회 시
[GET]/point/select
 - Request
{
    user_id
}
   - Response 
{
    user_point
}

//충전 시
[PATCH]/point/charge
 - Request
{
    user_id , charge_point
}
   - Response 
{
    message : "충전에 성공하였습니다."  
}
```

<h2>결제 API</h2>

```
[PATCH]/payment/
 - Request
{
    reserve_id,user_id
}
   - Response 
{
    //성공시
     message : "결제되었습니다." 
     
    //실패시
     message : "잔액이 부족합니다." 
}
```
