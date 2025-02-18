package com.ggplay149.concert.domain.user;

import com.ggplay149.concert.base.lockHandler.LockHandler;
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
    public Integer getPoint(Long userId) {
        Integer point = 0;
        lockHandler.lock("user"+userId,3,1);
        try {
            point = userReader.getPoint(userId);
        }finally {
            lockHandler.unlock("user"+userId);
        }
        return point;
    }

    @Transactional
    public void chargePoint(Long userId, Integer point){
        lockHandler.lock("user"+userId,3,1);
        try {
            Integer currentPoint = getPoint(userId);
            userPointCharger.chargePoint(userId, currentPoint + point);
        }finally {
            lockHandler.unlock("user"+userId);
        }
    }

    @Transactional
    public void usePoint(Long userId, Integer concertPrice){
        if (getPoint(userId) - concertPrice < 0) throw new RuntimeException("잔액이 부족합니다.");
        lockHandler.lock("user"+userId,3,1);
        try {
            chargePoint(userId, concertPrice * -1);
        }finally {
            lockHandler.unlock("user"+userId);
        }
    }

}
