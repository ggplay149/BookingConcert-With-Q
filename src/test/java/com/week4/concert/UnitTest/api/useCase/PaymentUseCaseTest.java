package com.week4.concert.UnitTest.api.useCase;

import com.week4.concert.Fixtures;
import com.week4.concert.api.useCase.PaymentUseCase;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.payment.PaymentService;
import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.WaitingService;
import com.week4.concert.domain.reservation.Reservation;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PaymentUseCaseTest {

    private PaymentService paymentService;
    private ReservationService reservationService;
    private ConcertService concertService;
    private UserService userService;

    private PaymentUseCase paymentUseCase;

    @BeforeEach
    void setUp() {
        paymentService = mock(PaymentService.class);
        reservationService = mock(ReservationService.class);
        concertService = mock(ConcertService.class);
        userService = mock(UserService.class);
        paymentUseCase = new PaymentUseCase(paymentService,reservationService,concertService,userService);
    }

    @Test
    @DisplayName("결제 성공")
    void success_payment(){
        //given

        Reservation testResrvation = Fixtures.reservation("아이유콘서트");
        String title = testResrvation.title();
        String date = testResrvation.reservationDate();
        String number = testResrvation.reservationNumber();
        Long userId = testResrvation.userId();

        Concert testConcert = Fixtures.concert("아이유콘서트");

        given(reservationService.validReservationNumber(number)).willReturn(testResrvation);
        given(concertService.getConcertInfo(any(),any())).willReturn(testConcert);

        //when
        String result = paymentUseCase.pay(number,userId);
        //then
        assert result == "정상 결제되었습니다. 예약이 확정되었습니다.";
    }

}
