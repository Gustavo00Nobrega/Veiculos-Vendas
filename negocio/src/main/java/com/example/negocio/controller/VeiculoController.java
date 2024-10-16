package com.example.negocio.controller;

import com.example.negocio.dtos.VeiculoRequestDTO;
import com.example.negocio.service.VeiculoServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoServices veiculoServices;

    @Autowired
    public VeiculoController(VeiculoServices veiculoServices) {
        this.veiculoServices = veiculoServices;
    }

    @GetMapping
    @Operation(summary = "Lista todos os veículos")
    public List<VeiculoRequestDTO> getAllVeiculos() {
        return veiculoServices.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um veículo por ID")
    public ResponseEntity<VeiculoRequestDTO> getVeiculoById(@PathVariable Integer id) {
        VeiculoRequestDTO veiculo = veiculoServices.getVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo veículo")
    public ResponseEntity<Void> cadastrarVeiculo(@RequestBody @Valid VeiculoRequestDTO data) {
        veiculoServices.postVeiculo(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados de um veículo existente")
    public ResponseEntity<VeiculoRequestDTO> atualizarVeiculo(@PathVariable Integer id, @RequestBody @Valid VeiculoRequestDTO data) {
        VeiculoRequestDTO veiculoAtualizado = veiculoServices.updateVeiculo(id, data);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um veículo existente")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Integer id) {
        veiculoServices.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}


