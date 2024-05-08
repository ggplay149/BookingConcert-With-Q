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

    public Boolean checkUserStatus(Long userId){
        return queueReader.checkUserStatus(userId);
    }

    public void insert(Long userId){
        if(queueReader.checkUserStatus(userId)){
            throw new RuntimeException("이미 활성화된 유저입니다.");
        }
        queueAppender.insertWait(userId);
    }

    public void updateQueue(){

        Long activeUsers = queueReader.countActive();

        if(activeUsers < maxActiveUsers){

            Long additionalUserNum = maxActiveUsers-activeUsers;

            String[] additionalUsers = queueReader.getTopNFromWait(additionalUserNum);

            for(String user : additionalUsers){

                queueAppender.insertActive(Long.parseLong(user));

                queueRemover.removeWait(Long.parseLong(user));

            }

        }
    }

}
