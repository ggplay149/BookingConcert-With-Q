package com.week4.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Concert {
    private Long id;
    private String title;
    private Integer capacity;
    private Integer reservedCount;
    private Integer price;
    private String date;
}
