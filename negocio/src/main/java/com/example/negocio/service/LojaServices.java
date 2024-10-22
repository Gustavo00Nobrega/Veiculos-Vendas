package com.example.negocio.service;


import com.example.negocio.domain.Funcionario;
import com.example.negocio.domain.Loja;
import com.example.negocio.dtos.FuncionarioDTO;
import com.example.negocio.dtos.LojaRequestDTO;
import com.example.negocio.repository.FuncionarioRepository;
import com.example.negocio.repository.LojaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaServices {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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

    public FuncionarioDTO createFuncionario(Integer lojaId, FuncionarioDTO funcionarioDTO) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + lojaId));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setSexoEnum(funcionarioDTO.sexoEnum());
        funcionario.setCpf(funcionarioDTO.cpf());
        funcionario.setDataNascimento(funcionarioDTO.dataNascimento());
        funcionario.setLoja(loja);

        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(
                savedFuncionario.getNome(),
                savedFuncionario.getSexoEnum(),
                savedFuncionario.getCpf(),
                savedFuncionario.getDataNascimento(),
                savedFuncionario.getLoja().getId()
        );
    }

    public Optional<List<FuncionarioDTO>> getAllFunc(Integer id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + id));
        List<FuncionarioDTO> funcionariosDTO = loja.getFuncionarios().stream()
                .map(func -> new FuncionarioDTO(
                        func.getNome(),
                        func.getSexoEnum(),
                        func.getCpf(),
                        func.getDataNascimento(),
                        func.getLoja().getId()))
                .toList();
        return Optional.of(funcionariosDTO);
    }

    public void deleteFuncionario(Integer lojaId, Integer funcionarioId) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com ID: " + lojaId));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + funcionarioId));

        if (!funcionario.getLoja().equals(loja)) {
            throw new IllegalStateException("Funcionário não pertence à loja especificada.");
        }

        funcionarioRepository.delete(funcionario);
    }

    public FuncionarioDTO updateFuncionario(Integer funcopnarioId, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioRepository.findById(funcopnarioId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado" + funcopnarioId));

        if(funcionarioDTO.nome() != null){
            funcionario.setNome(funcionarioDTO.nome());
        }
        if (funcionarioDTO.sexoEnum() != null){
            funcionario.setSexoEnum(funcionarioDTO.sexoEnum());
        }
        if(funcionarioDTO.cpf() != 0){
            funcionario.setCpf(funcionarioDTO.cpf());
        }
        if (funcionarioDTO.dataNascimento() != null){
            funcionario.setDataNascimento(funcionarioDTO.dataNascimento());
        }
        Funcionario updateFuncionario = funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(
                updateFuncionario.getNome(),
                updateFuncionario.getSexoEnum(),
                updateFuncionario.getCpf(),
                updateFuncionario.getDataNascimento(),
                updateFuncionario.getLoja().getId()
        );
    }
}
