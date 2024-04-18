package com.week4.concert.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

   // @Transactional
    @Modifying
    @Query("UPDATE UserEntity a SET a.point =:point WHERE a.userId =:id")
    void updatePoint(@Param("id") Long id, @Param("point") Integer point);

}
