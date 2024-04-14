package com.week4.concert.storage.concert;

import com.week4.concert.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {

    @Query("SELECT a FROM Concert a WHERE a.concert_date = :concert_date AND a.concert_title = :concert_title")
    Concert getConcertInfo(@Param("concert_date") String concert_date, @Param("concert_title") String concert_title);

    @Query("SELECT a FROM Concert a WHERE a.concert_reserved_count < a.concert_capacity")
    List<Concert> findAvailableDate();

}
