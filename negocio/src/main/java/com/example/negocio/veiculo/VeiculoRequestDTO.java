package com.example.negocio.veiculo;

import jakarta.persistence.Column;

public record VeiculoRequestDTO(
         int ano,

         String modelo,

         String marca,

         String cor
) {
}
