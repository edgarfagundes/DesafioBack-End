package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Localidade> findById(Long id) {
        if (localidadeRepository.existsById(id)) {
            return localidadeRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Não é possível localizar a localidade.");
        }
    }

    public Page<Localidade> findAll(Pageable pageable) {
        if (localidadeRepository.findAll(pageable).isEmpty()) {
            throw new IllegalArgumentException("Não existem localidades");
        }
        return localidadeRepository.findAll(pageable);
    }

    public Localidade save(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }


    public Localidade update(Long id, Localidade localidade) {
        if (localidadeRepository.existsById(id)) {
            this.localidadeRepository.findById(id).map(l -> {
                l.setNome(localidade.getNome());
                l.setLatitude(localidade.getLatitude());
                l.setLongitude(localidade.getLongitude());

                return this.localidadeRepository.save(l);
            });
            return localidade;
        } else {
            throw new IllegalArgumentException("Não foi possível atualizar localidade.");
        }
    }

    public void delete(Long id) {
        this.localidadeRepository.findById(id).map(l -> {
            if (!compromissoRepository.findAllByLocalidade(l).isEmpty()) {
                throw new IllegalArgumentException("Não pode excluir uma localidade com compromisso");
            } else {
                localidadeRepository.deleteById(id);
            }
            return "Apagado";
        });
    }
}
