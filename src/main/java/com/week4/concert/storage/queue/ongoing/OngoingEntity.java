package com.week4.concert.storage.queue.ongoing;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ongoing")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OngoingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,updatable = false)
    private Long id;

    @Column(name = "user_id" ,updatable = false)
    private Long userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "status" ,nullable = false)
    private String status;

    public Ongoing toOngoing(){
        return new Ongoing(getId(),userId,createdAt,status);
    }

}
