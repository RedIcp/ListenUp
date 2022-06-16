package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.LoginRequestDTO;
import com.listenup.individualassignment.dto.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    void login() throws Exception{
        LoginRequestDTO expectedRequest = LoginRequestDTO.builder()
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();
        when(service.login(expectedRequest))
                .thenReturn(LoginResponseDTO.builder()
                        .accessToken("h22hhs")
                        .build());

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "email": "yellow@gmail.com",
                                    "password": "123Yellow"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                    "accessToken":  "h22hhs"
                            }
                        """));

        verify(service).login(expectedRequest);
    }
}