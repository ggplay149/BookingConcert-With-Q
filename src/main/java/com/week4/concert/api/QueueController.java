package com.week4.concert.api;

import com.week4.concert.api.dto.QueueResponse;
import com.week4.concert.useCase.QueueUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Queue API Controller", description = "대기열 추가/조회 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("queue")
public class QueueController {

    private final QueueUseCase queueUseCase;

    @Operation(summary = "대기열 추가", description = "Parameter example) userId")
    @PostMapping("/{userId}/add")
    public ResponseEntity<QueueResponse> insertQueue(@PathVariable Long userId){

        queueUseCase.insertQueue(userId);

        return ResponseEntity.ok().body(QueueResponse.builder().message("대기열에 추가되었습니다.").build());
    }

    @Operation(summary = "대기열 조회", description = "Parameter example) userId")
    @GetMapping("/{userId}/check")
    public ResponseEntity<QueueResponse> findAvailableSeat(@PathVariable Long userId){

        return ResponseEntity.ok().body(QueueResponse.builder().message(queueUseCase.checkQueue(userId)).build());

    }
}
