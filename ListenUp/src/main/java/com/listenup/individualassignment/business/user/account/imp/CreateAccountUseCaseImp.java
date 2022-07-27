package com.listenup.individualassignment.business.user.account.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.user.account.CreateAccountUseCase;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.entity.RoleEnum;
import com.listenup.individualassignment.entity.User;
import com.listenup.individualassignment.entity.UserRole;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Primary
@RequiredArgsConstructor
public class CreateAccountUseCaseImp implements CreateAccountUseCase {
    private final UserRepository db;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponseDTO createAccount(CreateUserRequestDTO user){
        if(db.existsByEmail(user.getEmail())){
            throw new InvalidCustomerEmailException("EMAIL_EXIST");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User newUser = CustomerDTOConverter.convertToModelForCreate(user);

        newUser.setPassword(encodedPassword);
        newUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(newUser)
                        .role(RoleEnum.CUSTOMER)
                        .build()

        ));

        User savedUser = db.save(newUser);

        return CreateUserResponseDTO.builder()
                .userID(savedUser.getId())
                .build();
    }
}
