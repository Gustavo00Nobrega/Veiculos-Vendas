package com.example.negocio.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalhes_funcionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalhesFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalhes")
    private int id;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario")
    private double salario;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_funcionario")
//    private Funcionario funcionario;
}
