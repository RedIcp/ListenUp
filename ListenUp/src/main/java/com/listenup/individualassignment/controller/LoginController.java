package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.LoginRequestDTO;
import com.listenup.individualassignment.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final UserService management;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = management.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
