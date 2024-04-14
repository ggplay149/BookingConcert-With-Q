package com.week4.concert.domain.concert;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record Concert(

        Long id,
        String title,
        Integer capacity,
        Integer reservedCount,
        Integer price,
        String date
){
}

