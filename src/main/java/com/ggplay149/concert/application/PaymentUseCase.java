package com.ggplay149.concert.application;

import com.ggplay149.concert.domain.concert.Concert;
import com.ggplay149.concert.domain.concert.ConcertService;
import com.ggplay149.concert.domain.payment.PaymentService;
import com.ggplay149.concert.domain.payment.event.PaymentEvent;
import com.ggplay149.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentUseCase {

    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public String pay(String reservationNumber, Long userId) {

        Long reservedConcertId = reservationService.getReservedConcertId(reservationNumber);

        Concert reservedConcert = concertService.getConcertById(reservedConcertId);

        paymentService.pay(reservationNumber, userId);

        applicationEventPublisher.publishEvent(new PaymentEvent(reservationNumber, reservedConcert, userId));


        return "정상 결제되었습니다. 예약이 확정되었습니다.";
    }

}



