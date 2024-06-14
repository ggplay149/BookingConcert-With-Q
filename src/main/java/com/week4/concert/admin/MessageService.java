package com.week4.concert.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final WebClient.Builder webClientBuilder;

    public void send() {
        String response ="";
        try{
            response = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/message/1/send")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 응답을 기다리고 결과를 받음
        }catch (Exception e){
            //throw new RuntimeException(":: Message Exception ::");

        }
    }
}
