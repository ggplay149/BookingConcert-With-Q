package com.week4.concert.storage.user;


import com.week4.concert.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "point", nullable = false)
    private Integer point;

    public User toUser(){
        return new User(id,point);
    }

}
