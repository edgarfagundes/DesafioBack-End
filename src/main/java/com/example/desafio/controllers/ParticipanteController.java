package com.example.desafio.controllers;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.services.CompromissoService;
import com.example.desafio.models.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    ParticipanteService participanteService;

    @Autowired
    CompromissoService compromissoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Participante> findAll(Pageable pageable) {
        return participanteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Participante> findById(@PathVariable Long id) {
        return participanteService.findById(id);
    }

    @GetMapping("/compromissoParticipante")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Compromisso> listaCompromisso(@PathVariable Long id){
        return compromissoService.listaCompromissoParticipante(id);
    }

    @GetMapping("/compromissoParticipanteSituacao/{situacao}")
    @ResponseStatus(HttpStatus.OK)
    public List<Compromisso> listaCompromissoSituacao(@PathVariable Long id, @PathVariable Situacao situacao){
        return compromissoService.listaCompromissoParticipanteSituacao(id, situacao);
    }


    @PostMapping("/adicionarParticipante")
    @ResponseStatus(HttpStatus.CREATED)
    public Participante addParticipante(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    @PutMapping("/updateParticipante/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Participante updateParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        return participanteService.update(id, participante);
    }

    @DeleteMapping("/deleteParticipante/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipante(@PathVariable Long id){
        participanteService.delete(id);
    }
}
