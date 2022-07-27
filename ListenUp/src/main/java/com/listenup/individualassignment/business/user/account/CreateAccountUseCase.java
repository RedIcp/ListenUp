package com.listenup.individualassignment.business.user.account;

import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;

public interface CreateAccountUseCase {
    CreateUserResponseDTO createAccount(CreateUserRequestDTO user);
}
