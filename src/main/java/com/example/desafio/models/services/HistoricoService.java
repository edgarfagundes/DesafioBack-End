package com.example.desafio.models.services;

import com.example.desafio.models.entities.Historico;
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

    public Historico update(Long id, Historico historico) {
        this.historicoRepository.findById(id).map(h -> {
            if (historicoRepository.existsById(id)) {
                throw new IllegalArgumentException("Não rolou");
            }
           h.setCompromisso(historico.getCompromisso());
            h.setSituacao(historico.getSituacao());
            h.setData(historico.getData());

            return this.historicoRepository.save(h);
        });
        return null;
    }

    public void delete(Historico historico) {
        if (compromissoRepository.existsById(historico.getId())){
            historicoRepository.delete(historico);
        }
        throw new IllegalArgumentException("não rolou");
    }

    public Historico findHistorcoCompromisso(Long id) {
        return historicoRepository.findAllByCompromisso_Id(id);
    }
}
