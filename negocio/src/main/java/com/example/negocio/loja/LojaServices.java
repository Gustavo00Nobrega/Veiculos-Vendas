package com.example.negocio.loja;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaServices {

    @Autowired
    private LojaRepository lojaRepository;

    public LojaServices(LojaRepository lojaRepository){
        this.lojaRepository = lojaRepository;
    }

    public List<Loja> getAll() {
        return lojaRepository.findAll();
    }

    public void postLoja(LojaRequestDTO data) {
        Loja lojaData = new Loja(data);
        lojaRepository.save(lojaData);
    }

    public Loja updateLoja(Integer id, LojaRequestDTO data) {
        Optional<Loja> optionalLoja = lojaRepository.findById(id);
        if (optionalLoja.isPresent()) {
            Loja loja = optionalLoja.get();

            loja.setNome(data.nome());

            return lojaRepository.save(loja);
        } else {
            throw new EntityNotFoundException("Loja não encontrada com este ID: " + id);
        }
    }

    public void deleteLoja(Integer id) {
        if (lojaRepository.existsById(id)) {
            lojaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Loja não encontrada com este ID: " + id);
        }
    }
}
