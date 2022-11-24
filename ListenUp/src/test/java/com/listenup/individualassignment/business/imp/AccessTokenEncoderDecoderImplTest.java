package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.login.imp.AccessTokenEncoderDecoderImp;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.Key;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {
    @Mock
    private Key key;
    @InjectMocks
    private AccessTokenEncoderDecoderImp accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImp("E91E158E4C6656F68B1B5D1C311766DE98D2AD6EF3BFB33F78E9CFCDF9");

    @Test
    void encode() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .email("user")
                .roles(List.of("Customer"))
                .userID(0L)
                .build();
        String actualResult = accessTokenEncoderDecoder.encode(token);
        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }

    @Test
    void encode_NoUserID() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .email("user")
                .roles(List.of("Customer"))
                .userID(null)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);
        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }

    @Test
    void encode_NoRole() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .email("user")
                .roles(null)
                .userID(0L)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);
        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }


}