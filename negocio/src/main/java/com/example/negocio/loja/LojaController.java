package com.example.negocio.loja;

import com.example.negocio.veiculo.Veiculo;
import com.example.negocio.veiculo.VeiculoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Loja")
public class LojaController {

    private final LojaServices lojaServices;

    @Autowired
    public LojaController(LojaServices lojaServices){
        this.lojaServices = lojaServices;
    }

    @Autowired
    public LojaRepository repository;

    @GetMapping
    @Operation(summary = "Lista todas as lojas")
    public List<Loja> getAllLojas(){
        return lojaServices.getAll();
    }
    @PostMapping
    @Operation(summary = "Cadastra uma nova loja de veiculo")
    public void cadastrarLoja(@RequestBody @Valid LojaRequestDTO data) {
        lojaServices.postLoja(data);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Verifica se existe e edita dados da loja")
    public ResponseEntity<Loja> editarLoja(@PathVariable Integer id, @RequestBody @Valid LojaRequestDTO data) {
        Loja lojaAtualizada = lojaServices.updateLoja(id, data);
        return ResponseEntity.ok(lojaAtualizada);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Verifica se existe e deleta a loja")
    public ResponseEntity<Void> deletarLoja(@PathVariable Integer id) {
        lojaServices.deleteLoja(id);
        return ResponseEntity.noContent().build();
    }
}
