package com.week4.concert.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageMockController {

    @GetMapping("/{userId}/send")
    public String send(@PathVariable Long userId) {
        throw new RuntimeException(":: Message Send Exception ::");
    }
}
