package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.user.account.CreateAccountUseCase;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:3000")
public class SignUpController {
    private final CreateAccountUseCase createAccountUseCase;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO userDTO) {
        CreateUserResponseDTO user = createAccountUseCase.createAccount(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
