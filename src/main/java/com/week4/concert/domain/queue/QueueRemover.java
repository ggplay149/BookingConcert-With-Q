package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueRemover{

    private final QueueRepository queueRepository;

    public void removeWait(Long userId){
        queueRepository.removeWait(userId);
    }

    public void removeActive(Long userId){
        queueRepository.removeActive(userId);
    }

    public void reset(){
        queueRepository.reset();
    }

}
