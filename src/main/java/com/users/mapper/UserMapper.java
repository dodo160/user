package com.users.mapper;

import com.users.dto.UserDTO;
import com.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", constant = "HIDEN")
    UserDTO toDto(User entity);

    User fromDto(UserDTO dto);
}
