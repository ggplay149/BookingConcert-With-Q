package com.week4.concert.IntegrationTest;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.WaitingService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.useCase.QueueUseCase;
import com.week4.concert.useCase.ReservationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationIntegrationTest {

    @Autowired
    private ReservationUseCase reservationUseCase;


    @Test
    @DisplayName("해당콘서트 최대인원 조회후 예약된 좌석번호 빼주고 예약가능 리스트 리턴")
    void selectAvailableSeat() {
        //given : data.sql으로 1,4,7 좌석 예약, capacity : 50
        //when
        List<Integer> result = reservationUseCase.selectAvailableSeat("20241112","MuseConcert");
        //then
        assertThat(result.size()).isEqualTo(47);
        assertThat(result.get(0)).isEqualTo(2);
        assertThat(result.get(2)).isEqualTo(5);
    }
}
