package com.week4.concert.application;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.payment.PaymentService;
import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.reservation.Reservation;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {

    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;
    private final OngoingSerivce ongoingSerivce;

    @Transactional
    public String pay(String reservationNumber, Long userId) {

        Reservation validReservation = reservationService.validReservationNumber(reservationNumber);

        Integer concertPrice = concertService.getConcertInfo(validReservation.reservationDate(),validReservation.title()).price();

        userService.checkPoint(concertPrice,userId);

        paymentService.pay(reservationNumber, userId);

        reservationService.finalConfirm(reservationNumber);

        userService.usePoint(userId,concertPrice);

        ongoingSerivce.updateDone(userId);

        return "정상 결제되었습니다. 예약이 확정되었습니다.";
    }
}
