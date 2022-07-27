package com.listenup.individualassignment.business.user.account;

import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;

public interface UpdateProfileUseCase {
    UpdateUserDTO updateAccount(UpdateUserDTO user);
}
