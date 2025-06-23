package com.korit.authstudy.mapper;

import com.korit.authstudy.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UsersMapper {

    public int updateFullNameOrEmailById(User user);
    public int updatePassword(@Param("id") Integer userId, @Param("password") String newPassword);
}
