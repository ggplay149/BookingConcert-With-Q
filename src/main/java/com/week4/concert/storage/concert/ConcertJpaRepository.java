package com.week4.concert.storage.concert;

import com.week4.concert.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {

    Optional<Concert> findByDateAndTitle(String date, String title);

    @Query("SELECT a FROM ConcertEntity a WHERE a.reservedCount < a.capacity")
    Optional<List<Concert>> findAvailableConcertAndDate();

}
