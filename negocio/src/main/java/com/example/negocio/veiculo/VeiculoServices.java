package com.example.negocio.veiculo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServices {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoServices(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> getAll() {
        return veiculoRepository.findAll();
    }

    public void postVeiculo(VeiculoRequestDTO data) {
        Veiculo veiculoData = new Veiculo(data);
        veiculoRepository.save(veiculoData);
    }

    public Veiculo updateVeiculo(Integer id, VeiculoRequestDTO data) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();

            veiculo.setAno(data.ano());
            veiculo.setModelo(data.modelo());
            veiculo.setMarca(data.marca());
            veiculo.setCor(data.cor());

            return veiculoRepository.save(veiculo);
        } else {
            throw new EntityNotFoundException("Veículo não encontrado com o ID: " + id);
        }
    }

    public void deleteVeiculo(Integer id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Veículo não encontrado com o ID: " + id);
        }
    }


}
