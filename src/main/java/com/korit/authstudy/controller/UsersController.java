package com.korit.authstudy.controller;

import com.korit.authstudy.dto.UserRegisterDto;
import com.korit.authstudy.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto dto) {
        log.info("DTO: {}", dto);
        return ResponseEntity.ok(usersService.register(dto));
    }
}
