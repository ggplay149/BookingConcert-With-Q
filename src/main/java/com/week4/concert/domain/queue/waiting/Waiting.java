package com.week4.concert.domain.queue.waiting;

import java.time.LocalDateTime;

public record Waiting(
        Long id,
        Long userId,
        LocalDateTime createdAt,
        String status
) {
    public Waiting changeStatus(String status){
        return new Waiting(id(),userId(),createdAt(),status);
    }
}
