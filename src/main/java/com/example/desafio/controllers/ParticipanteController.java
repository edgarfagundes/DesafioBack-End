package com.example.desafio.controllers;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import com.example.desafio.models.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    ParticipanteService participanteService;

    @GetMapping
    public Page<Participante> findAll(Pageable pageable) {
        return participanteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Participante> findById(@PathVariable Long id) {
        return participanteService.findById(id);
    }

    @PostMapping("/adicionarParticipante")
    public Participante addParticipante(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    @PutMapping("/updateParticipante/{id}")
    public Participante updateParticipante(@PathVariable Long id,@RequestBody Participante participante) {
        return participanteService.update(id, participante);
    }

    @DeleteMapping("/deleteParticipante/{id}")
    public void deleteParticipante(@PathVariable Long id){
        participanteService.delete(id);
    }
}
