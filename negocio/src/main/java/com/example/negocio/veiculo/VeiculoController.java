package com.example.negocio.veiculo;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Veiculo")
public class VeiculoController {

    private final VeiculoServices veiculoServices;
    @Autowired
    public VeiculoRepository repository;

    @Autowired
    public VeiculoController(VeiculoServices veiculoServices) {
        this.veiculoServices = veiculoServices;
    }
    @GetMapping
    @Operation(summary = "Lista todos os veiculos")
    public List<Veiculo> getAllVeiculo(){
        return veiculoServices.getAll();
    }
    @PostMapping
    @Operation(summary = "Cadastra um novo veiculo")
    public void cadastrarVeiculo(@RequestBody @Valid VeiculoRequestDTO data) {
        veiculoServices.postVeiculo(data);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Verifica se existe e edita o veiculo")
    public ResponseEntity<Veiculo> editarVeiculo(@PathVariable Integer id, @RequestBody @Valid VeiculoRequestDTO data) {
        Veiculo veiculoAtualizado = veiculoServices.updateVeiculo(id, data);
        return ResponseEntity.ok(veiculoAtualizado);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Verifica se existe e deleta o veiculo")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Integer id) {
        veiculoServices.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
