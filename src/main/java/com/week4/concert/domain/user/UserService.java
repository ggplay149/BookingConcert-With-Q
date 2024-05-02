package com.week4.concert.domain.user;

import com.week4.concert.base.lockHandler.LockHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserPointCharger userPointCharger;
    private final LockHandler lockHandler;

    @Transactional(readOnly = true)
    public Integer getPoint(Long userId){
        return userReader.getPoint(userId);
    }

    @Transactional(readOnly = true)
    public void checkPoint(Integer concertPrice,Long userId){
        if(getPoint(userId)-concertPrice < 0 )
            throw new RuntimeException("잔액이 부족합니다.");
    }

    @Transactional
    public void chargePoint(Long userId, Integer point){
            Integer currentPoint = getPoint(userId);
            userPointCharger.chargePoint(userId, currentPoint+point);
    }

    @Transactional
    public void usePoint(Long userId, Integer point){
        chargePoint(userId, point*-1);
    }
}
