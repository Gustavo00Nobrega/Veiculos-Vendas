package com.example.negocio.service;

import com.example.negocio.domain.Veiculo;
import com.example.negocio.dtos.VeiculoRequestDTO;
import com.example.negocio.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServices {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoServices(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<VeiculoRequestDTO> getAll() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                .map(veiculo -> new VeiculoRequestDTO(
                        veiculo.getAno(),
                        veiculo.getModelo(),
                        veiculo.getMarca(),
                        veiculo.getCor()))
                .toList();
    }

    public VeiculoRequestDTO getVeiculoById(Integer id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com ID: " + id));

        return new VeiculoRequestDTO(veiculo.getAno(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getCor());
    }

    public void postVeiculo(VeiculoRequestDTO data) {
        Veiculo veiculo = new Veiculo();
        veiculo.setAno(data.ano());
        veiculo.setModelo(data.modelo());
        veiculo.setMarca(data.marca());
        veiculo.setCor(data.cor());
        veiculoRepository.save(veiculo);
    }

    public VeiculoRequestDTO updateVeiculo(Integer id, VeiculoRequestDTO data) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com ID: " + id));

        veiculo.setAno(data.ano());
        veiculo.setModelo(data.modelo());
        veiculo.setMarca(data.marca());
        veiculo.setCor(data.cor());

        veiculoRepository.save(veiculo);
        return new VeiculoRequestDTO(veiculo.getAno(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getCor());
    }

    public void deleteVeiculo(Integer id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Veículo não encontrado com ID: " + id);
        }
    }
}
