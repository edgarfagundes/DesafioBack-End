package com.example.desafio.models.services;

import com.example.desafio.models.Historico;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    HistoricoRepository historicoRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Historico> findById(Long id) {
        try {
            return historicoRepository.findById(id);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Page<Historico> findAll(Pageable pageable) {
        try {
            return historicoRepository.findAll(pageable);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Historico save(Historico historico) {
        try {
            return historicoRepository.save(historico);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Historico update(Historico historico) {
        try {
            return historicoRepository.save(historico);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Historico historico) {
        if (compromissoRepository.existsById(historico.getId())){
            historicoRepository.delete(historico);
        }
        throw new IllegalArgumentException("não rolou");
    }
}
