package com.week4.concert.domain.concert;

public record Concert(

        Long id,
        String title,
        Integer capacity,
        Integer reservedCount,
        Integer price,
        String date
) {

}

