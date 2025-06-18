package com.korit.authstudy.service;

import com.korit.authstudy.domain.entity.Member;
import com.korit.authstudy.dto.MemberRegisterDto;
import com.korit.authstudy.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembersService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MembersRepository membersRepository;

    public Member register(MemberRegisterDto dto) {
        Member insertedMember = membersRepository.save(dto.toEntity(passwordEncoder));
        return insertedMember;
    }
}
