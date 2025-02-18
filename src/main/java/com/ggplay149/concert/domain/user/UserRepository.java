package com.ggplay149.concert.domain.user;

public interface UserRepository {

    Integer getPoint(Long userId);

    void chargePoint(Long userId, Integer addPoint);

}
