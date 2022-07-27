package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Participante;

import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        return Optional.empty();
    }

    public List<Participante> findAll() {
        try {
            return participanteRepository.findAll();
        } catch (NullPointerException n) {
            n.getMessage();
            return participanteRepository.findAll();
        }
    }

    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Participante update(Long id, Participante participante) {
        this.participanteRepository.findById(id).map(p -> {
            p.setNome(participante.getNome());
            p.setTelefone(participante.getTelefone());

            return this.participanteRepository.save(p);
        }).orElseThrow(IllegalArgumentException::new);
        return participante;
    }

    public void delete(Long id) {
        this.participanteRepository.findById(id).map(p -> {
            this.participanteRepository.deleteById(id);
            return id;
        });
    }


}

