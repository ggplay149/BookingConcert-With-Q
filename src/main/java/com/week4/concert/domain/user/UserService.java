package com.week4.concert.domain.user;

import com.week4.concert.base.lockHandler.LockHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserPointCharger userPointCharger;
    private final LockHandler lockHandler;

    public Integer getPoint(Long userId){ return userReader.getPoint(userId); }

    public void chargePoint(Long userId, Integer point){
        Integer currentPoint = getPoint(userId);
        if (lockHandler.lock(userId,10)) {
            userPointCharger.chargePoint(userId, currentPoint+point);
        } else {
            throw new RuntimeException("10초내에 연속으로 충전할수 없습니다.");
        }
    }

    public void checkPoint(Integer concertPrice,Long userId){
        if(getPoint(userId)-concertPrice < 0 )
            throw new RuntimeException("잔액이 부족합니다.");
    }
}
