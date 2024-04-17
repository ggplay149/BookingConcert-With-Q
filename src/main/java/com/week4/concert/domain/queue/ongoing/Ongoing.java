package com.week4.concert.domain.queue.ongoing;

import java.time.LocalDateTime;

public record Ongoing(
        Long id,
        Long userId,
        LocalDateTime createdAt
) {
}
