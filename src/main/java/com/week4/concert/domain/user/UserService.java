package com.week4.concert.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserPointCharger userPointCharger;

    public Integer getPoint(Long userId){ return userReader.getPoint(userId); }

    public void chargePoint(Long userId, Integer point){
        Integer currentPoint = getPoint(userId);
        userPointCharger.chargePoint(userId, currentPoint+point);
    }
}
