package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.user.action.GetUsersUseCase;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class GetUsersUseCaseImp implements GetUsersUseCase {
    private final UserRepository db;

    @Override
    public List<ViewUserDTO> getUsers(){
        return CustomerDTOConverter.convertToDTOList(db.findAll());
    }
}
