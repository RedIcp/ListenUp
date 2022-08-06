package com.listenup.individualassignment.business.user.account.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.account.UpdateProfileUseCase;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.repository.entity.User;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class UpdateProfileUseCaseImp implements UpdateProfileUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public UpdateUserDTO updateAccount(UpdateUserDTO user){
        User old = db.getById(user.getId());

        authorised.isAuthorised(user.getId());

        if(!db.existsById(user.getId())){
            throw new InvalidCustomerException("INVALID_ID");
        }
        if(user.getEmail().equals(old.getEmail())){
            db.save(CustomerDTOConverter.convertToModelForUpdate(user));
        }
        else {
            if(db.existsByEmail(user.getEmail())){
                throw new InvalidCustomerEmailException("EMAIL_EXIST");
            }
            db.save(CustomerDTOConverter.convertToModelForUpdate(user));
        }
        return user;
    }
}
