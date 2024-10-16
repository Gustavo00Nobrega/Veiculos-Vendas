package com.example.negocio.service;


import com.example.negocio.domain.Loja;
import com.example.negocio.dtos.FuncionarioDTO;
import com.example.negocio.dtos.LojaRequestDTO;
import com.example.negocio.repository.LojaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaServices {

    @Autowired
    private LojaRepository lojaRepository;

    public LojaServices(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public List<LojaRequestDTO> getAll() {
        List<Loja> lojas = lojaRepository.findAll();
        return lojas.stream().map(loja -> new LojaRequestDTO(loja.getNome())).toList();
    }

    public LojaRequestDTO getLojaById(Integer id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + id));
        return new LojaRequestDTO(loja.getNome());
    }

    public void postLoja(LojaRequestDTO data) {
        Loja loja = new Loja();
        loja.setNome(data.nome());
        lojaRepository.save(loja);
    }

    public LojaRequestDTO updateLoja(Integer id, LojaRequestDTO data) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + id));

        loja.setNome(data.nome());
        lojaRepository.save(loja);

        return new LojaRequestDTO(loja.getNome());
    }

    public void deleteLoja(Integer id) {
        if (lojaRepository.existsById(id)) {
            lojaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Loja não encontrada com ID: " + id);
        }
    }

    public Optional<List<FuncionarioDTO>> getAllFunc(Integer id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + id));

        List<FuncionarioDTO> funcionariosDTO = loja.getFuncionarios().stream()
                .map(func -> new FuncionarioDTO(
                        func.getId(),
                        func.getNome(),
                        func.getSexoEnum(),
                        func.getCpf(),
                        func.getDataNascimento()))
                .toList();

        return Optional.of(funcionariosDTO);
    }
}
