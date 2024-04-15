package com.week4.concert.api.concert.dto;

public record ConcertRequest(
        String title,
        String date
) {
    public static ConcertRequest form(String date,String title){
        return new ConcertRequest(date,title);
    }
}
