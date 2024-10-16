package com.example.negocio.controller;

import com.example.negocio.dtos.FuncionarioDTO;
import com.example.negocio.service.FuncionarioServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioServices funcionarioServices;

    @Autowired
    public FuncionarioController(FuncionarioServices funcionarioServices) {
        this.funcionarioServices = funcionarioServices;
    }

    @GetMapping
    @Operation(summary = "Lista todos os funcionários")
    public List<FuncionarioDTO> getAllFuncionarios() {
        return funcionarioServices.getAll();
    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Busca um funcionário por ID")
//    public ResponseEntity<FuncionarioDTO> getFuncionarioById(@PathVariable Integer id) {
//        FuncionarioDTO funcionario = funcionarioServices.getFuncionarioById(id);
//        return ResponseEntity.ok(funcionario);
//    }

    @PostMapping
    @Operation(summary = "Cadastra um novo funcionário")
    public ResponseEntity<Void> cadastrarFuncionario(@RequestBody @Valid FuncionarioDTO data) {
        funcionarioServices.postFuncionario(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados de um funcionário existente")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Integer id, @RequestBody @Valid FuncionarioDTO data) {
        FuncionarioDTO funcionarioAtualizado = funcionarioServices.updateFuncionario(id, data);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um funcionário existente")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id) {
        funcionarioServices.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}

