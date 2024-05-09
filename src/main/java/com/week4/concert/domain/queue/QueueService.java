package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QueueService {

    @Value("${MAX_ACTIVE_USER}")
    private Long maxActiveUsers;

    @Value("${EXPIRTAION_TIME}")
    private Long ExpirationTime;

    private final QueueAppender queueAppender;
    private final QueueRemover queueRemover;
    private final QueueReader queueReader;

    public String checkUserStatus(Long userId) {

        if (queueReader.checkUserStatus(userId, "Active")) return "활성화된 유저입니다.";

        else if (queueReader.checkUserStatus(userId, "Wait")) return "대기중인 유저입니다.";

        return "대기중인 유저가 아닙니다.";

    }

    public void insert(Long userId) {

        if (queueReader.checkUserStatus(userId, "Active")) {
            throw new RuntimeException("이미 활성화된 유저입니다.");
        }

        queueAppender.insert(userId, "Wait");
    }

    public void insertNewActiveUsers() {

        Long activeUsers = queueReader.countActiveUsers();

        if (activeUsers < maxActiveUsers) {

            Long additionalUserNum = maxActiveUsers - activeUsers;

            String[] additionalUsers = queueReader.getNewActiveUsers(additionalUserNum);

            for (String user : additionalUsers) {

                queueAppender.insert(Long.parseLong(user), "Active");

                queueRemover.remove(Long.parseLong(user), "Wait");

            }

        }
    }

    public void removeExpiredActiveUsers(){

        Set<ZSetOperations.TypedTuple<String>> activeUsers =  queueReader.getCurrentActiveUsers();

        for(ZSetOperations.TypedTuple<String> user : activeUsers){

            if(Instant.now().getEpochSecond()-user.getScore() > ExpirationTime * 60){

                queueRemover.remove(Long.parseLong(user.getValue()),"Active");

            }
        }
    }
}
