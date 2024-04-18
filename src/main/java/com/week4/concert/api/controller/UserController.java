package com.week4.concert.api.controller;

import com.week4.concert.api.dto.UserResponse;
import com.week4.concert.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserPoint API Controller", description = "유저포인트 조회/충전 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저포인트 조회", description = "Parameter example) userId")
    @GetMapping("/{userId}/getPoint")
    public ResponseEntity<UserResponse> getPoint(@PathVariable Long userId) {

        return ResponseEntity.ok().body(UserResponse
                .builder()
                .point(userService.getPoint(userId))
                .build());
    }

    @Operation(summary = "유저포인트 충전", description = "Parameter example) userId , point")
    @PostMapping("/{userId}/chargePoint")
    public ResponseEntity<UserResponse> chargePoint(@PathVariable Long userId,
                                                    @RequestParam("point") Integer point) {
        userService.chargePoint(userId,point);
        return ResponseEntity.ok().body(UserResponse
                .builder()
                .message("충전되었습니다.")
                .build());
    }
}
