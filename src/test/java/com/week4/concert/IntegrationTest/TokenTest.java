package com.week4.concert.IntegrationTest;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OngoingSerivce ongoingSerivce;

    @Test
    @Transactional
    @DisplayName("도메인 관련 api 호출시 토큰 검증, waiting이면 에러")
    public void when_fail_token_check_then_error() throws Exception {
        //given
        final String url = "/concert/1/availableConcert";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().is(500))
                .andExpect(result -> assertThat(result.getResolvedException()
                        .getMessage()).isEqualTo("활성화된 유저가 아닙니다."));
    }

    @Test
    @Transactional
    @DisplayName("도메인 관련 api 호출시 토큰 검증, Ongoing 이면 성공")
    public void when_success_token_check_then_status_200() throws Exception {
        //given
        ongoingSerivce.insert(55L);
        final String url = "/concert/55/availableConcert";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().is(200));
    }
}
