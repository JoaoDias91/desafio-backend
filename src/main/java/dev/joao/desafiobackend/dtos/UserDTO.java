package dev.joao.desafiobackend.dtos;

import dev.joao.desafiobackend.domain.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType type) {
}
