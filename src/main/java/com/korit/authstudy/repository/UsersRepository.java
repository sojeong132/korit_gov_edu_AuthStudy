package com.korit.authstudy.repository;

import com.korit.authstudy.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
