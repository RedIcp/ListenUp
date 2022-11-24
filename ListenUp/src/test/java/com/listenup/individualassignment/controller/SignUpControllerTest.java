package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.user.account.CreateAccountUseCase;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
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
class SignUpControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAccountUseCase service;

    @Test
    void createUser() throws Exception{
        CreateUserRequestDTO user = CreateUserRequestDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        CreateUserResponseDTO response = CreateUserResponseDTO.builder()
                .userID(1L)
                .build();

        when(service.createAccount(user)).thenReturn(response);

        mockMvc.perform(post("/signup")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "username": "Yellow",
                                        "email": "yellow@gmail.com",
                                        "password": "123Yellow"
                            }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                        "userID": 1
                            }
                        """));

        verify(service).createAccount(user);
    }
}