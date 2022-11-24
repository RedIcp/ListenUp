package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;

import java.util.List;

public interface GetUsersUseCase {
    List<ViewUserDTO> getUsers();
}
