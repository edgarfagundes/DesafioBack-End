package com.example.desafio.models.services;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CompromissoService {

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Compromisso> findById(Long id) {
        try {
            return compromissoRepository.findById(id);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Page<Compromisso> findAll(Pageable pageable) {
        try {
            return compromissoRepository.findAll(pageable);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Compromisso save(Compromisso compromisso) {
        try {
            return compromissoRepository.save(compromisso);
        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Compromisso update(Long id, Compromisso compromisso) {
        this.compromissoRepository.findById(id).map(c -> {
            if (compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Não rolou");
            }
            c.setDataHora(compromisso.getDataHora());
            c.setDescricao(compromisso.getDescricao());
            c.setParticipantes(compromisso.getParticipantes());
            c.setLocalidade(compromisso.getLocalidade());
            c.setSituacao(compromisso.getSituacao());
            return this.compromissoRepository.save(c);
        });
        return null;
    }

    public void delete(Long id) {
        this.compromissoRepository.findById(id).map(c -> {
            if (compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Entity cannot be deleted");
            }
             else {
                 this.compromissoRepository.deleteById(id);
             }
            return null;
        });
    }
}
