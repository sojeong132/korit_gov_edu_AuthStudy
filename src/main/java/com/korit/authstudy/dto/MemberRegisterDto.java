package com.korit.authstudy.dto;

import com.korit.authstudy.domain.entity.Member;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class MemberRegisterDto {

    private String username;
    private String password;
    private String fullName;
    private String email;

    public Member toEntity(BCryptPasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberName(username)
                .password(passwordEncoder.encode(password))
                .name(fullName)
                .email(email)
                .build();
    }
}
