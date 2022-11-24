package com.listenup.individualassignment.business.login;

import com.listenup.individualassignment.dto.LoginRequestDTO;
import com.listenup.individualassignment.dto.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO request);
}
