package com.ggplay149.concert.infrastructure.concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {

    Optional<ConcertEntity> findByDateAndTitle(String date, String title);

    @Query("SELECT a FROM ConcertEntity a WHERE a.reservedCount < a.capacity")
    Optional<List<ConcertEntity>> findAvailableConcertAndDate();

}
