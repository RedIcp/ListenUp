package com.listenup.individualassignment.business;


import com.listenup.individualassignment.dto.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}
