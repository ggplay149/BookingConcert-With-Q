package com.week4.concert.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPointCharger {

    private final UserRepository userRepository;

    public void chargePoint(Long userId, Integer addPoint) {
        System.out.println("충전됨!!!!!"+addPoint);
        userRepository.chargePoint(userId, addPoint);

    }
}
