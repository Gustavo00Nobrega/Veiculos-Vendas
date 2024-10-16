package com.example.negocio.domain;

import com.example.negocio.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "funcionario")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexoenum")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexoEnum;

    @Column(unique = true, name = "cpf")
    private int cpf;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_loja")
    private Loja loja;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "detalhes_id")
    private DetalhesFuncionario detalhesFuncionario;
}

