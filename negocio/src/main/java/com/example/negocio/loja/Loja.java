package com.example.negocio.loja;

import com.example.negocio.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Loja")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loja {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    @Column(name = "id_loja")
    private int id;

    @Column(name = "nome_loja")
    private String nome;

    public Loja(LojaRequestDTO data){
        this.nome = data.nome();
    }

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "Veiculo_id")
//    private Veiculo veiculo;
}
