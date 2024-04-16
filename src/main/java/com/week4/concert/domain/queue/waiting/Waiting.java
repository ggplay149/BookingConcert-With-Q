package com.week4.concert.domain.queue.waiting;

import java.time.LocalDateTime;

public record Waiting(
        Long id,
        Long userId,
        LocalDateTime createdAt
) {
}
