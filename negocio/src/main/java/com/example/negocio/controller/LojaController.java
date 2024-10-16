package com.example.negocio.controller;

import com.example.negocio.dtos.LojaRequestDTO;
import com.example.negocio.service.LojaServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    private final LojaServices lojaServices;

    @Autowired
    public LojaController(LojaServices lojaServices) {
        this.lojaServices = lojaServices;
    }

    @GetMapping
    @Operation(summary = "Lista todas as lojas")
    public List<LojaRequestDTO> getAllLojas() {
        return lojaServices.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma loja por ID")
    public ResponseEntity<LojaRequestDTO> getLojaById(@PathVariable Integer id) {
        LojaRequestDTO lojaDTO = lojaServices.getLojaById(id);
        return ResponseEntity.ok(lojaDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova loja de veículos")
    public ResponseEntity<Void> cadastrarLoja(@RequestBody @Valid LojaRequestDTO data) {
        lojaServices.postLoja(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados de uma loja existente")
    public ResponseEntity<LojaRequestDTO> updateLoja(@PathVariable Integer id, @RequestBody @Valid LojaRequestDTO data) {
        LojaRequestDTO lojaAtualizada = lojaServices.updateLoja(id, data);
        return ResponseEntity.ok(lojaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma loja existente")
    public ResponseEntity<Void> deletarLoja(@PathVariable Integer id) {
        lojaServices.deleteLoja(id);
        return ResponseEntity.noContent().build();
    }
}


