package com.example.negocio.dtos;

import com.example.negocio.enums.SexoEnum;

import java.time.LocalDate;

public record FuncionarioDTO(int id, String nome, SexoEnum sexoEnum, int cpf, LocalDate dataNascimento) {
}
