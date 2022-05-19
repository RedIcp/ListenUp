package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}
