package com.example.demo.dto;

import java.time.LocalDate;

public record UserRequestDTO(
        String name,
        String email,
        LocalDate birth
) {}