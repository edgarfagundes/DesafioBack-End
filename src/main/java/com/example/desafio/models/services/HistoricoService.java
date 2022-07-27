package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Historico;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    HistoricoRepository historicoRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Historico> findById(Long id) {
        Boolean c = compromissoRepository.existsById(id);
        if (c.equals(true)){
            return historicoRepository.findById(id);
        }
        throw new IllegalArgumentException("Id não encontrado.");
    }

    public Page<Historico> findAll(Pageable pageable) {
        if (compromissoRepository.findAll(pageable).isEmpty()){
            throw new IllegalArgumentException("Não existem compromissos");
        }
        return historicoRepository.findAll(pageable);
    }
    public Historico save(Historico historico) {
            return historicoRepository.save(historico);
    }

    public Historico update(Long id, Historico historico) {
        if (historico.getId().equals(id)){
        this.historicoRepository.findById(id).map(h -> {
                h.setCompromisso(historico.getCompromisso());
                h.setSituacao(historico.getSituacao());
                h.setData(historico.getData());

                return this.historicoRepository.save(h);
        });
        }else {
            throw new IllegalArgumentException("Erro ao salvar alterações");
        }

        return historico;
    }

    public void delete(Historico historico) {
        if (compromissoRepository.existsById(historico.getId())) {
            historicoRepository.delete(historico);
        }
        throw new IllegalArgumentException("Não é possível deletar histórico.");
    }

    public Historico findHistorcoCompromisso(Long id) {
        if (compromissoRepository.existsById(id)){
        return historicoRepository.findAllByCompromisso_Id(id);
    }
        throw new IllegalArgumentException("Id não encontrado");
    }

    public List<Historico> findAllByCompromisso(Long id){
        if (historicoRepository.existsById(id)) {
            return historicoRepository.findAllByCompromisso(id);
        }
        throw new IllegalArgumentException("Id não encontrado");
    }

    public void deleteAllByCompromisso(Compromisso compromisso){
        if (compromissoRepository.existsById(compromisso.getId())) {
            historicoRepository.deleteAllByCompromisso(compromisso);
        }
        throw new IllegalArgumentException("Id não encontrado");
    }


}
