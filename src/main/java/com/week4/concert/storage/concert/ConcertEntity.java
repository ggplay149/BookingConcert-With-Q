package com.week4.concert.storage.concert;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "concert")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,updatable = false)
    private Long id;

    @Column(name = "title" ,nullable = false)
    private String title;

    @Column(name = "capacity" ,nullable = false)
    private Integer capacity;

    @Column(name = "reservedCount" ,nullable = false)
    private Integer reservedCount;

    @Column(name = "price" ,nullable = false)
    private Integer price;

    @Column(name = "date" ,nullable = false)
    private String date;
}
