package com.example.desafio.models.services;

import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
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
        return Optional.empty();
    }

    public Page<Localidade> findAll(Pageable pageable) {
        try {
            return localidadeRepository.findAll(pageable);
        } catch (NullPointerException n) {
            n.getMessage();
            return localidadeRepository.findAll(pageable);
        }
    }

    public Localidade save(Localidade localidade) {
        try {
            return localidadeRepository.save(localidade);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

        public Localidade update(Long id, Localidade localidade) {
             this.localidadeRepository.findById(id).map(l -> {
                    l.setNome(localidade.getNome());
                    l.setLatitude(localidade.getLatitude());
                    l.setLongitude(localidade.getLongitude());

                    return this.localidadeRepository.save(l);
            }).orElseThrow(IllegalArgumentException::new);
             return localidade;
    }

    public void delete(Long id) {
        this.localidadeRepository.findById(id).map(l -> {
                this.localidadeRepository.deleteById(id);
                return id;
        }).orElseThrow(NullPointerException::new);
    }
}
