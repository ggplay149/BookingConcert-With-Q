package com.week4.concert.infrastructure.concert;

import com.week4.concert.domain.concert.Concert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "concert")
@Getter
@Setter
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

    @Column(name = "reserved_count" ,nullable = false)
    private Integer reservedCount;

    @Column(name = "price" ,nullable = false)
    private Integer price;

    @Column(name = "concert_date" ,nullable = false)
    private String date;

    public Concert toConcert(){
        return new Concert(getId(),title,capacity,reservedCount,price,date);
    }
}
