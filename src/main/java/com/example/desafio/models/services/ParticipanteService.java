package com.example.desafio.models.services;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;

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
import java.util.Optional;

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

    public Participante save(Participante participante) {
        try {
            return participanteRepository.save(participante);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Participante update(Long id, Participante participante) {
        try {
            return participanteRepository.save(participante);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Long id) {
        participanteRepository.findById(id).map( p -> {
            if(compromissoRepository.findAllByParticipantes(p).isEmpty()){
                Participante participante = participanteRepository.findById(id).get();
                participanteRepository.delete(participante);
            }
            throw new NullPointerException("Não pode");
        });
    }
}
