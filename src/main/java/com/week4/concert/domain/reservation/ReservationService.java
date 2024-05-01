package com.week4.concert.domain.reservation;

import com.week4.concert.base.lockHandler.LockHandler;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.infrastructure.reservation.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationReader reservationReader;
    private final ReservationAppender reservationAppender;
    private final LockHandler lockHandler;

    public List<Integer> reservedSeat(String date, String title) {
        return reservationReader.reservedSeat(date, title);
    }


    public List<Integer> availableSeat(String date, String title, Integer capacity) {

        List<Integer> reserved = reservedSeat(date, title);
        List<Integer> availableSeat = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) availableSeat.add(i);

        //총 좌석리스트에서 예약된 좌석 빼주기
        for (int i = 0; i < reserved.size(); i++) {
            int temp = availableSeat.indexOf(reserved.get(i));
            availableSeat.remove(temp);
        }
        return availableSeat;
    }

    public void reserve(String reservationNumber, Long concertId) {
        if(!lockHandler.lock(reservationNumber,concertId.toString(),300)){
            throw new RuntimeException("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        }
    }


    public void finalConfirm(String reservationNumber, String concertTitle, Long userId) {

        String[] temp = reservationNumber.split("\\.");
        String concertDate = temp[0];
        Integer seatNum = Integer.parseInt(temp[2]);

        reservationAppender.reserve(ReservationEntity
                .builder()
                .reservationNumber(reservationNumber)
                .seatNum(seatNum)
                .userId(userId)
                .title(concertTitle)
                .reservationDate(concertDate)
                .finalConfirm("Y")
                .build());
    }

    public Long getReservedConcertId(String reservationNumber) {
        String concertId = lockHandler.getValue(reservationNumber);
        if(concertId==null){
            throw new RuntimeException("존재하지 않거나 유효시간이 만료된 예약번호 입니다.");
        } else{
            return Long.parseLong(concertId);
        }
    }

    public void checkReservation(String reservationNumber) {
        reservationReader.checkReservation(reservationNumber);
    }
}
