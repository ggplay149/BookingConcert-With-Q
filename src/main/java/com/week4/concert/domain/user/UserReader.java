package com.week4.concert.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public Integer getPoint(Long userId) {
        System.out.println("잔액!!!!!"+userRepository.getPoint(userId));
        return userRepository.getPoint(userId);
    }
}
