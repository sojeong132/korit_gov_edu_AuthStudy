package com.korit.authstudy.repository;

import com.korit.authstudy.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);

    // JPQL
    @Query(value = """
            update User
            set fullName = :#{#user.fullName}, email = :#{#user.email}
            where id = :#{#user.id}
            """)
    @Modifying(clearAutomatically = true)
    int updateFullNameOrEmailById(@Param("user") User user);
}
