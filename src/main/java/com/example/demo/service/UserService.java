package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        Optional<User> isPresent = userRepository.findByEmail(user.getEmail());
        if (isPresent.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        user.setAge(Period.between(user.getBirth(), LocalDate.now()).getYears());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        Optional<User> isPresent = userRepository.findById(id);
        if (isPresent.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        userRepository.delete(isPresent.get());
    }

    @Transactional
    public void update(Long id, String email, String name) {
        Optional<User> isPresent = userRepository.findById(id);
        if (isPresent.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        User user = isPresent.get();
        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if (foundByEmail.isPresent()) {
                throw new IllegalStateException("User with email " + email + " already exists");
            }
            user.setEmail(email);
        }
        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }
    }
}