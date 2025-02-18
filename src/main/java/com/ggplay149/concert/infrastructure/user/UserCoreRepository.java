package com.ggplay149.concert.infrastructure.user;

import com.ggplay149.concert.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Integer getPoint(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("존재 하지 않는 아이디 입니다."))
                .toUser()
                .getPoint();
    }

    @Override
    public void chargePoint(Long id, Integer point) {
        userJpaRepository.updatePoint(id, point);
    }

}
