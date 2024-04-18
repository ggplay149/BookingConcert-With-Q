package com.week4.concert;

import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fixtures {
    public static Concert concert(String name){
        if(name.equals("아이유콘서트")){
            return new Concert(1L,"아이유콘서트",50,0,50000,"20240414");
        }
        if(name.equals("실리카겔콘서트")){
            return new Concert(2L,"실리카겔콘서트",50,50,65000,"20240516");
        }
        if(name.equals("성시경콘서트")){
            return new Concert(3L,"성시경콘서트",50,29,70000,"20240807");
        }
        if(name.equals("오아시스콘서트")){
            return new Concert(4L,"오아시스콘서트",50,50,60000,"20241214");
        }
        return null;
    }

    public static List<Integer> reservedList(String name){
        if(name.equals("아이유콘서트")){
            List<Integer> list = new ArrayList<>();
            for(int i = 1 ; i <=10 ; i++) list.add(i);
            return list;
        }
        return null;
    }

    public static Ongoing ongoing(Long userId){
        if(userId == 1L){
          return new Ongoing(1L,userId, LocalDateTime.now(),"Ongoing");
        }
        if(userId == 2L){
            return new Ongoing(2L,userId, LocalDateTime.now(),"Ongoing");
        }
        if(userId == 3L){
            return new Ongoing(3L,userId, LocalDateTime.now(),"Ongoing");
        }
        return null;
    }
    public static Waiting waiting(Long userId){
        if(userId == 1L){
            return new Waiting(1L,userId, LocalDateTime.now(),"Waiting");
        }
        if(userId == 2L){
            return new Waiting(2L,userId, LocalDateTime.now(),"Waiting");
        }
        if(userId == 3L){
            return new Waiting(3L,userId, LocalDateTime.now(),"Waiting");
        }
        return null;
    }

    public static Reservation reservation(String name){
        if(name.equals("아이유콘서트")){
            return new Reservation(15L,"2024050325","20240503","아이유콘서트",5,24L,"N");
        }
        return null;
    }
}
