package com.week4.concert.application;

import com.week4.concert.admin.MessageService;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.payment.PaymentService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {


    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;
    private final MessageService messageService;

    @Transactional
    public String pay(String reservationNumber, Long userId) {

        Long reservedConcertId = reservationService.getReservedConcertId(reservationNumber);

        Concert reservedConcert = concertService.getConcertById(reservedConcertId);

        userService.checkPoint(reservedConcert.price(), userId);

        paymentService.pay(reservationNumber, userId);

        userService.usePoint(userId, reservedConcert.price());

        reservationService.finalizeConfirmation(reservationNumber, reservedConcert.title(), userId);

        concertService.increaseReservationCount(reservedConcert.id());

        messageService.send();

        return "정상 결제되었습니다. 예약이 확정되었습니다.";
    }

}



