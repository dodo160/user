package com.users.service;

import com.users.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    User findById(@NotNull Integer id);

    List<User> findAll();

    User add(@NotNull User entity);

    User update(@NotNull User entity);

    void delete(@NotNull Integer id);
}
