package com.week4.concert.integrationTest;

import com.week4.concert.application.ReservationUseCase;
import com.week4.concert.base.lockHandler.LockHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ReservationTest {

    @Autowired
    private ReservationUseCase reservationUseCase;

    @Autowired
    private LockHandler lockHandler;

    @BeforeEach
    void resetRedis(){
        lockHandler.reset();
    }

    @Test
    @DisplayName("해당콘서트 최대인원 조회후 예약된 좌석번호 빼주고 예약가능 리스트 리턴")
    void select_available_seat() {

        //given : data.sql으로 1,4,7 좌석 예약, capacity : 50

        //when
        List<Integer> result = reservationUseCase.selectAvailableSeat("20241112", "MuseConcert");

        //then
        assertThat(result.size()).isEqualTo(46);
        assertThat(result.get(0)).isEqualTo(2);
        assertThat(result.get(2)).isEqualTo(5);
    }

    @Test
    @DisplayName("이미 예약확정된 날짜/콘서트/좌석 실패")
    void fail_reserve_duplicate_reservation() {

        //given : data.sql 로 예약 확정 데이터 insert

        //when : 같은 날짜,콘서트,좌석으로 다시 예약
        Exception result = assertThrows(RuntimeException.class
                ,() -> reservationUseCase.reserve("20241112","MuseConcert",3L,4));

        //then
        assertThat(result.getMessage()).isEqualTo("예약된 좌석입니다.");
    }

    @Test
    @DisplayName("예약성공")
    void success_reserveation() {

        //given

        //when
        String result = reservationUseCase.reserve("20241112","MuseConcert",3L,49);

        //then
        assertThat(result).isEqualTo("[ 예약번호 : 20241112.5.49 ] 5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
    }
}
