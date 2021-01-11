package com.example.code.validation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("hello 컨트롤러에 파라미터를 전달 안하면 name validation 이 작동한다")
    void helloTest1() throws Exception {

        // when
        ResultActions resultActions = mvc.perform(get("/hello")
        );

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(((BindException) mvcResult.getResolvedException()).getBindingResult().getFieldErrors())
                .extracting("field", "defaultMessage")
                .containsExactlyInAnyOrder(
                        tuple("name", "이름은 빈값일 수 없습니다.")
                );
    }

    @Test
    @DisplayName("hello 컨트롤러에 name 값을 빈값으로 전달하면 name validation 이 작동한다")
    void helloTest2() throws Exception {

        // when
        ResultActions resultActions = mvc.perform(get("/hello")
                .param("name", "")
        );

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(((BindException) mvcResult.getResolvedException()).getBindingResult().getFieldErrors())
                .extracting("field", "defaultMessage")
                .containsExactlyInAnyOrder(
                        tuple("name", "이름은 빈값일 수 없습니다.")
                );
    }
}
