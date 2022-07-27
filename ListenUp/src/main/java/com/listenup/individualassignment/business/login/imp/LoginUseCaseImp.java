package com.listenup.individualassignment.business.login.imp;

import com.listenup.individualassignment.business.login.AccessTokenEncoder;
import com.listenup.individualassignment.business.login.LoginUseCase;
import com.listenup.individualassignment.business.exception.InvalidCredentialsException;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.dto.LoginRequestDTO;
import com.listenup.individualassignment.dto.LoginResponseDTO;
import com.listenup.individualassignment.entity.User;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class LoginUseCaseImp implements LoginUseCase {
    private final UserRepository db;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request){
        if(!db.existsByEmail(request.getEmail())){
            throw new InvalidCustomerEmailException("EMAIL_DOES_NOT_EXIST");
        }
        User user = db.getByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public String generateAccessToken(User user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .email(user.getEmail())
                        .roles(roles)
                        .userID(user.getId())
                        .build());
    }
}
