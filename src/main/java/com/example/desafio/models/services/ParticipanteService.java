package com.example.desafio.models.services;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;

import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ParticipanteService {

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Participante> findById(Long id) {
        try {
            return participanteRepository.findById(id);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Page<Participante> findAll(Pageable pageable) {
        try {
            return participanteRepository.findAll(pageable);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return null;
    }

    public Participante save(Participante participante, Compromisso compromisso) {

        return participante;
    }

    public Participante update(Long id, Participante participante) {
        if (participante.getId().equals(id)){
            return participanteRepository.save(participante);
        }
        throw new NullPointerException("num quero não");
    }

    public void delete(Long id) {
         participanteRepository.findById(id).filter(p ->
                compromissoRepository.findAllByParticipantes(p).stream().anyMatch(compromisso -> p.equals(compromisso.getParticipantes())))
                 .orElseThrow();
    }
    

    
}

