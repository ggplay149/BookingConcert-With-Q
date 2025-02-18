package com.ggplay149.concert.infrastructure.reservation;

import com.ggplay149.concert.domain.reservation.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationCoreRepository implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Integer> selectReservedSeat(String date, String title) {
        return reservationJpaRepository.selectReservedSeat(date, title)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new EntityNotFoundException("전석 예약 가능합니다."));
    }

    @Override
    public void reserve(ReservationEntity reservation) {
        try {
            reservationJpaRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("이미 예약된 좌석입니다.");
        }
    }

    @Override
    public void checkDuplicateReservation(String reservationNumber) {
        if(reservationJpaRepository.checkReservation(reservationNumber)!= null)
            throw new RuntimeException("예약된 좌석입니다.");
    }

    @Override
    public Boolean createTemporaryReservation(String key, String value, long ttl) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key, value, Duration.ofSeconds(ttl));
    }

    @Override
    public String validateExpiration(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeTemporaryReservation(String key) {
        redisTemplate.delete(key);
    }
}

