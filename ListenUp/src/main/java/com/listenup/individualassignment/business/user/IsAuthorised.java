package com.listenup.individualassignment.business.user;

import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.repository.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IsAuthorised {
    private AccessTokenDTO requestAccessToken;

    public void isAuthorised(long id){
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && requestAccessToken.getUserID() != id) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
    }
}
