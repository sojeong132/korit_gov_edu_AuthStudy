package com.korit.authstudy.service;

import com.korit.authstudy.domain.entity.User;
import com.korit.authstudy.dto.UserRegisterDto;
import com.korit.authstudy.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public User register(UserRegisterDto dto) {
        User insertedUser = usersRepository.save(dto.toEntity(passwordEncoder));
        return insertedUser;
    }
}
