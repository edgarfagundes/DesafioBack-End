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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompromissoService {

    @Autowired
    CompromissoRepository compromissoRepository;

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    HistoricoService historicoService;

    public Compromisso findById(Long id) {
        return compromissoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
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
        List<Compromisso> compromissos = compromissoRepository.findAllByParticipantes(compromisso.getParticipantes().iterator().next());
        if (!compromissos.isEmpty()) {
            throw new IllegalArgumentException("Não pode mais de um compromisso por participante");
        }
        return compromissoRepository.save(compromisso);
    }

    public Compromisso update(Long id, Compromisso compromisso) {
        Historico historico = new Historico();
        this.compromissoRepository.findById(id).map(c -> {
            if (compromissoRepository.findById(id).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.findById(id).get().getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Não rolou");
            }

            c.setDataHora(compromisso.getDataHora());
            c.setDescricao(compromisso.getDescricao());
            c.setParticipantes(compromisso.getParticipantes());
            c.setLocalidade(compromisso.getLocalidade());
            c.setSituacao(compromisso.getSituacao());
            Compromisso compromissoSave = this.compromissoRepository.save(c);

            historico.setCompromisso(compromissoSave);
            historico.setData(c.getDataHora());
            historico.setSituacao(c.getSituacao());
            historicoService.save(historico);
            return compromissoSave;
        });
        return compromisso;
    }

    public void delete(Long id) {
        this.compromissoRepository.findById(id).map(c -> {
            if (compromissoRepository.findById(id).get().getSituacao().equals(Situacao.EXECUTADO) ||
                    compromissoRepository.findById(id).get().getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Entity cannot be deleted");
            } else {
                this.compromissoRepository.deleteById(id);
                return null;
            }
        });
    }

    public List<Compromisso> listaCompromissoParticipanteSituacao(Long id, Situacao situacao) {
        Participante participante = participanteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return compromissoRepository.findAllByParticipantes(participante).stream()
                .filter(compromisso -> compromisso.getSituacao().equals(situacao))
                .collect(Collectors.toList());
    }

    public List<Compromisso> listaCompromissoParticipante(Long id) {
        Participante participante = participanteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return compromissoRepository.findAllByParticipantes(participante);
    }

    public boolean validateParticipante(Participante participante, Compromisso compromisso) {
        return this.compromissoRepository.findAllByParticipantes(participante)
                .stream()
                .anyMatch(c -> Situacao.PENDENTE.equals(compromisso.getSituacao()) && compromisso.getDataHora().equals(c.getDataHora()));
    }


}
