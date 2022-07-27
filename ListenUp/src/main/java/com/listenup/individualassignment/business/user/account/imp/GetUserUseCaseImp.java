package com.listenup.individualassignment.business.user.account.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.account.GetUserUseCase;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetUserUseCaseImp implements GetUserUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public UpdateUserDTO getUser(long id){
        authorised.isAuthorised(id);
        return CustomerDTOConverter.convertToDTOForUpdate(db.getById(id));
    }
}
