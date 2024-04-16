package com.week4.concert.storage.queue.waiting;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.waiting.Waiting;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "waiting")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WaitingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,updatable = false)
    private Long id;

    @Column(name = "user_id" ,updatable = false)
    private Long userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Waiting toWaiting(){
        return new Waiting(getId(),userId,createdAt);
    }

}