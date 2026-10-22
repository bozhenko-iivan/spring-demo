package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setBirth(userRequestDTO.birth());

        User savedUser = userService.create(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getBirth(),
                savedUser.getAge()
        );
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable (name = "id") Long id) {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update (@PathVariable Long id,
                        @RequestParam (required = false) String email,
                        @RequestParam (required = false) String name) {
        userService.update(id, email, name);
    }
}
