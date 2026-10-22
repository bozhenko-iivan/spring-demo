package com.example.demo.dto;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        LocalDate birth,
        Integer age
) { }
