package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Historico;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompromissoService {

    @Autowired
    CompromissoRepository compromissoRepository;

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    HistoricoService historicoService;

    public Optional<Compromisso> findById(Long id) {
        if (compromissoRepository.existsById(id)) {
            return compromissoRepository.findById(id);
        }else {
            throw new IllegalArgumentException("Id não encontrado");
        }
    }

    public List<Compromisso> findAll() {
        try {
            return compromissoRepository.findAll();
        } catch (NullPointerException n) {
            n.getMessage();
            return compromissoRepository.findAll();
        }
    }

    public Compromisso save(Compromisso compromisso) {
            return compromissoRepository.save(compromisso);
    }

    public Compromisso update(Long id, Compromisso compromisso) {
        Historico historico = new Historico();
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
            Compromisso compromissoSave = this.compromissoRepository.save(c);

            historico.setCompromisso(compromissoSave);
            historico.setData(LocalDateTime.now());
            historico.setSituacao(c.getSituacao());
            historicoService.save(historico);
            return compromissoSave;
        });
        return compromisso;
    }

    public void delete(Long id) {
        this.compromissoRepository.findById(id).map(c -> {
            if (compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.existsById(id) && compromissoRepository.findById(id).get().getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Entity cannot be deleted");
            } else {
                this.compromissoRepository.deleteById(id);
                return null;
            }
        });
    }

    public List<Compromisso> listaCompromissoParticipanteSituacao(Long id, Situacao situacao) {
        return compromissoRepository.findById(id).stream()
                .filter(compromisso -> compromisso.getSituacao().equals(situacao))
                .collect(Collectors.toList());
    }

    public Optional<Compromisso> listaCompromissoParticipante(Long id) {
        return compromissoRepository.findById(id);
    }

    public boolean validateParticipante(Participante participante, Compromisso compromisso) {
        return this.compromissoRepository.findAllByParticipantes(participante)
                .stream()
                .anyMatch(c -> Situacao.PENDENTE.equals(compromisso.getSituacao()) && compromisso.getDataHora().equals(c.getDataHora()));
    }


}
