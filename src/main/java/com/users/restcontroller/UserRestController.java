package com.users.restcontroller;

import com.users.dto.UserDTO;
import com.users.mapper.UserMapper;
import com.users.model.User;
import com.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Integer id) {
        return ResponseEntity.ok(userMapper.toDto(userService.findById(id)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll().stream().map(userMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody final UserDTO dto) {
        final User user = userService.add(userMapper.fromDto(dto));
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(userMapper.toDto(user));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody final UserDTO dto) {
        return ResponseEntity.ok(userMapper.toDto(userService.update(userMapper.fromDto(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
