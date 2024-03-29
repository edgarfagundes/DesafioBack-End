package com.example.desafio.models.services;

import com.example.desafio.models.entities.Participante;

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
        if (participanteRepository.existsById(id)) {
            return participanteRepository.findById(id);
        }
        throw new IllegalArgumentException("Id não encontrado.");
    }

    public Page<Participante> findAll(Pageable pageable) {
        if (participanteRepository.findAll(pageable).isEmpty()) {
            throw new IllegalArgumentException("Não existem participantes");
        }
        return participanteRepository.findAll(pageable);
    }

    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Participante update(Long id, Participante participante) {
        if (participanteRepository.existsById(id)) {
            this.participanteRepository.findById(id).map(p -> {
                p.setNome(participante.getNome());
                p.setTelefone(participante.getTelefone());

                return this.participanteRepository.save(p);
            });
            return participante;
        }
        throw new IllegalArgumentException("Erro ao salvar alterações");
    }

    public void delete(Long id) {
        this.participanteRepository.findById(id).map(p -> {
            if (!compromissoRepository.findAllByParticipantes(p).isEmpty()) {
                throw new IllegalArgumentException("Não pode excluir um participante com compromisso");
            } else {
                participanteRepository.deleteById(id);
            }
            return "Apagado";
        });
    }
}

