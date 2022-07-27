package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Historico;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.exceptions.ExceptionHandlerClass;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompromissoService {

    private final
    CompromissoRepository compromissoRepository;

    private final
    ParticipanteRepository participanteRepository;

    private final
    HistoricoService historicoService;

    public CompromissoService(CompromissoRepository compromissoRepository, ParticipanteRepository participanteRepository, HistoricoService historicoService, ExceptionHandlerClass exceptionHandlerClass) {
        this.compromissoRepository = compromissoRepository;
        this.participanteRepository = participanteRepository;
        this.historicoService = historicoService;
    }

    public Optional<Compromisso> findById(Long id) {
         Boolean c = compromissoRepository.existsById(id);
         if (c.equals(true)){
             return compromissoRepository.findById(id);
         }
         throw new IllegalArgumentException("Id não encontrado.");
    }

    public List<Compromisso> findAll() {
        if (compromissoRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("Não existem compromissos");
        }
        return compromissoRepository.findAll();
    }

    public Compromisso save(Compromisso compromisso) {
        List<Compromisso> compromissos = compromissoRepository.findAllByParticipantes(compromisso.getParticipantes().iterator().next());
        if ((!compromissos.isEmpty()) || compromissos.stream().iterator().next().getSituacao().equals(Situacao.PENDENTE)) {
            throw new IllegalArgumentException("Não pode mais de um compromisso por participante");
        } else {
            return compromissoRepository.save(compromisso);
        }
    }

    public void update(Long id, Compromisso compromisso) {
        Historico historico = new Historico();
        this.compromissoRepository.findById(id).map(c -> {
            if (c.getSituacao().equals(Situacao.EXECUTADO) || c.getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Compromisso não pode estar executado ou cancelado.");
            } else {
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
            }
        });
    }

    public void delete(Long id) {
        Compromisso compromisso = compromissoRepository.findById(id).orElseThrow(NullPointerException::new);
        this.compromissoRepository.findById(id).map(c -> {
            if (c.getSituacao().equals(Situacao.EXECUTADO) ||
                    c.getSituacao().equals(Situacao.CANCELADO)) {
                throw new IllegalArgumentException("Entidade não pode ser deletada.");
            } else {
                this.historicoService.deleteAllByCompromisso(compromisso);
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
