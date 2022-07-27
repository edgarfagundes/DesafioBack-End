package com.example.desafio.models.services;

import com.example.desafio.models.entities.Localidade;
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
        if (localidadeRepository.existsById(id)) {
            localidadeRepository.findById(id);
        }
        throw new IllegalArgumentException("Não é possível deletar histórico.");
    }


    public Page<Localidade> findAll(Pageable pageable) {
        if (localidadeRepository.findAll(pageable).isEmpty()){
            throw new IllegalArgumentException("Não existem compromissos");
        }
        return localidadeRepository.findAll(pageable);
    }

    public Localidade save(Localidade localidade) {
           return localidadeRepository.save(localidade);
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
        if (localidadeRepository.existsById(id)) {
            localidadeRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Não é possível deletar histórico.");
    }
}
