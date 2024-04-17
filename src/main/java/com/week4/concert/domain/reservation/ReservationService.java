package com.week4.concert.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationReader reservationReader;

    public List<Integer> reservedSeat(String date, String title){
        return reservationReader.reservedSeat(date,title);
    }

    public List<Integer> availableSeat(String date, String title, Integer capacity){

        List<Integer> reserved = reservedSeat(date,title);

        List<Integer> availableSeat = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) availableSeat.add(i);

        //총 좌석리스트에서 예약된 좌석 빼주기
        for (int i = 0; i < reserved.size(); i++) {
            int temp = availableSeat.indexOf(reserved.get(i));
            availableSeat.remove(temp);
        }

        return availableSeat;
    }
}
