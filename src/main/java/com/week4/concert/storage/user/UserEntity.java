package com.week4.concert.storage.user;


import com.week4.concert.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "point", nullable = false)
    private Integer point;

    public User toUser(){
        return new User(userId,point);
    }

}
