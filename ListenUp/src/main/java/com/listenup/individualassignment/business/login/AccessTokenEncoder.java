package com.listenup.individualassignment.business.login;

import com.listenup.individualassignment.dto.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}
