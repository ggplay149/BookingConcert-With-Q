package com.ggplay149.concert;

import com.ggplay149.concert.domain.concert.Concert;
import com.ggplay149.concert.domain.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Fixtures {
    public static Concert concert(String name) {
        if (name.equals("아이유콘서트")) {
            return new Concert(1L, "아이유콘서트", 50, 0, 50000, "20240414");
        }
        if (name.equals("실리카겔콘서트")) {
            return new Concert(2L, "실리카겔콘서트", 50, 50, 65000, "20240516");
        }
        if (name.equals("성시경콘서트")) {
            return new Concert(3L, "성시경콘서트", 50, 29, 70000, "20240807");
        }
        if (name.equals("오아시스콘서트")) {
            return new Concert(4L, "오아시스콘서트", 50, 50, 60000, "20241214");
        }
        return null;
    }

    public static List<Integer> reservedList(String name) {
        if (name.equals("아이유콘서트")) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) list.add(i);
            return list;
        }
        return null;
    }

    public static Reservation reservation(String name) {
        if (name.equals("아이유콘서트")) {
            return new Reservation(15L, "2024050325", "20240503", "아이유콘서트", 5, 24L, "N");
        }
        return null;
    }
}
