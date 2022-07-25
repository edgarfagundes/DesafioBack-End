package com.example.desafio.models.services;

import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Localidade> findById(Long id) {
        try {
            return localidadeRepository.findById(id);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Page<Localidade> findAll(Pageable pageable) {
        try {
            return localidadeRepository.findAll(pageable);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Localidade save(Localidade participante) {
        try {
            return localidadeRepository.save(participante);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Localidade update(Localidade localidade) {
        try {
            return localidadeRepository.save(localidade);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Long id) {
        this.localidadeRepository.findById(id).map(l -> {
            if (localidadeRepository.existsById(id) ) {
                throw new IllegalArgumentException("Entity cannot be deleted");
            }
            else {
                this.localidadeRepository.deleteById(id);
            }
            return null;
        });
    }
}
