package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {

    @Value("${MAX_ACTIVE_USER}")
    private Long maxActiveUsers;

    private final QueueAppender queueAppender;
    private final QueueRemover queueRemover;
    private final QueueReader queueReader;

    public void insert(Long userId){
        queueAppender.insertWait(userId);
    }

    public void updateQueue(){
        Long activeUsers = queueReader.countActive();
        if(activeUsers < maxActiveUsers){
            Long additionalUserNum = maxActiveUsers-activeUsers;


        }
    }
}
