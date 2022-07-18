package com.example.desafio.models.services;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
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

    public Compromisso save(Compromisso compromisso, Participante participante) {
        this.compromissoRepository.findById(participante.getId()).map(c -> {
            if (compromissoRepository.existsById(participante.getId()) && compromissoRepository.findById(participante.getId()).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.existsById(participante.getId()) && compromissoRepository.findById(participante.getId()).get().getSituacao().equals(Situacao.CANCELADO)) {
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

    public Compromisso update(Participante participante, Compromisso compromisso) {
        this.compromissoRepository.findById(participante.getId()).map(c -> {
            if (compromissoRepository.existsById(participante.getId()) && compromissoRepository.findById(participante.getId()).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.existsById(participante.getId()) && compromissoRepository.findById(participante.getId()).get().getSituacao().equals(Situacao.CANCELADO)) {
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

    public boolean validateParticipante(Participante participante, Compromisso compromisso) {
        return this.compromissoRepository.findAllByParticipantes(participante)
                .stream()
                .anyMatch(c -> Situacao.PENDENTE.equals(compromisso.getSituacao()) && compromisso.getDataHora().equals(c.getDataHora()));
    }
}
