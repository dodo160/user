package com.users.service;

import com.users.exception.DuplicateUserException;
import com.users.exception.NotFoundException;
import com.users.model.User;
import com.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User entity doesn't exist with id: " + id));
    }

    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User add(final User entity) {
        if (userRepository.existsByUsername(entity.getUsername())) {
            throw new DuplicateUserException("Resource already exists");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public User update(final User entity) {
        final User user = findById(entity.getId());
        user.setName(entity.getName());
        user.setUsername(entity.getUsername());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(final Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User entity doesn't exist with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
