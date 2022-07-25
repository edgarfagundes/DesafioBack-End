package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Participante;

import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            return participanteRepository.save(participante);
    }

    public Participante update(Long id, Participante participante) {
        this.participanteRepository.findById(id).map(p -> {
            if (compromissoRepository.existsById(id)) {
                throw new IllegalArgumentException("Não rolou");
            }
            p.setNome(participante.getNome());
            p.setTelefone(participante.getTelefone());
            Participante participanteSave = this.participanteRepository.save(p);

            return participanteSave;
        });
        return null;
    }

    public void delete(Long id) {
        this.participanteRepository.findById(id).map(p -> {
            if (participanteRepository.existsById(id)) {
                throw new IllegalArgumentException("Entity cannot be deleted");
            }
            else {
                this.participanteRepository.deleteById(id);
            }
            return null;
        });
    }
    

    
}

