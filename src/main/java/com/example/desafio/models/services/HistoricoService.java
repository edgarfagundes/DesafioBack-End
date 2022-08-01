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
        Optional<Historico> h = historicoRepository.findById(id);
        Boolean c = compromissoRepository.existsById(h.get().getCompromisso().getId());
        if (c.equals(true)){
            return h;
        }else {
            throw new IllegalArgumentException("Id não encontrado.");
        }
    }

    public Page<Historico> findAll(Pageable pageable) {
        if (historicoRepository.findAll(pageable).isEmpty()){
            throw new IllegalArgumentException("Não existem históricos");
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
            historicoRepository.delete(historico);
    }

    public Historico findHistorcoCompromisso(Long id) {
        return historicoRepository.findAllByCompromisso_Id(id);
    }

    public List<Historico> findAllByCompromisso(Compromisso compromisso){
            return historicoRepository.findAllByCompromisso(compromisso);
    }

    public void deleteAllByCompromisso(Compromisso compromisso){
            historicoRepository.deleteAllByCompromisso(compromisso);
    }


}
