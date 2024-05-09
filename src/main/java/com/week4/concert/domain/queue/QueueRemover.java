package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueRemover{

    private final QueueRepository queueRepository;

    public void remove(Long userId, String key){
        queueRepository.remove(userId,key);
    }

    public void reset(){
        queueRepository.reset();
    }

}
