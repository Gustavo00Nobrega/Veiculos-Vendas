package com.example.negocio.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "loja")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loja")
    private int id;

    @Column(name = "nome_loja")
    private String nome;

    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios = new ArrayList<>();
}
