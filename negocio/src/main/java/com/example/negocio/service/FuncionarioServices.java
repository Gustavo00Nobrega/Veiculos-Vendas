package com.example.negocio.service;

import com.example.negocio.domain.Funcionario;
import com.example.negocio.dtos.FuncionarioDTO;
import com.example.negocio.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServices {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioServices(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioDTO> getAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(func -> new FuncionarioDTO(
                        func.getId(),
                        func.getNome(),
                        func.getSexoEnum(),
                        func.getCpf(),
                        func.getDataNascimento()))
                .toList();
    }

//    public FuncionarioDTO getFuncionarioById(Integer id) {
//        Funcionario funcionario = funcionarioRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));
//
//        // Crie o DTO a partir da entidade Funcionario
//        return new FuncionarioDTO(
//                funcionario.getId(),
//                funcionario.getNome(),
//                funcionario.getSexoEnum(),
//                funcionario.getCpf(),
//                funcionario.getDataNascimento(),
//                funcionario.getLoja().getId());
//    }

    public void postFuncionario(FuncionarioDTO data) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(data.nome());
        funcionario.setSexoEnum(data.sexoEnum());
        funcionario.setCpf(data.cpf());
        funcionario.setDataNascimento(data.dataNascimento());
        funcionarioRepository.save(funcionario);
    }

    public FuncionarioDTO updateFuncionario(Integer id, FuncionarioDTO data) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));

        funcionario.setNome(data.nome());
        funcionario.setSexoEnum(data.sexoEnum());
        funcionario.setCpf(data.cpf());
        funcionario.setDataNascimento(data.dataNascimento());

        funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(funcionario.getId(), funcionario.getNome(), funcionario.getSexoEnum(), funcionario.getCpf(), funcionario.getDataNascimento());
    }

    public void deleteFuncionario(Integer id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Funcionário não encontrado com ID: " + id);
        }
    }
}

