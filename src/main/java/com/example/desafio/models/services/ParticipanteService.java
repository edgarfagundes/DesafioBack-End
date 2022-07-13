package com.example.desafio.models.services;

import com.example.desafio.models.Participante;

import com.example.desafio.models.repository.ParticipanteRepository;
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
    CompromissoService compromissoService;

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

    public ResponseEntity<Participante> update(Participante participante) {
        try {
            return new ResponseEntity(participanteRepository.save(participante), HttpStatus.ACCEPTED);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Participante participante) {
        if (participante.equals(null)){
            throw new NullPointerException();
        } else {
            participanteRepository.delete(participante);
        }
    }
}
