package com.example.negocio.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "veiculos")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_veiculo")
    private int id;

    @Column(name = "ano_veiculo")
    private int ano;

    @Column(name = "modelo_veiculo")
    private String modelo;

    @Column(name = "marca_veiculo")
    private String marca;

    @Column(name = "cor_veiculo")
    private String cor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_loja")
    private Loja loja;

}
