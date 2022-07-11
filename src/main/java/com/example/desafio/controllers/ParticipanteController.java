package com.example.desafio.controllers;

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
@RequestMapping("/particpante")
public class ParticipanteController {

    @Autowired
    ParticipanteService participanteService;

    @GetMapping
    public ResponseEntity<Page<Participante>> findAll(Pageable pageable) {
        return participanteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Participante>> findById(@PathVariable Long id) {
        return participanteService.findById(id);
    }

    @PostMapping("/adicionarParticipante")
    public ResponseEntity<Participante> addParticipante(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    @PutMapping("/updateParticipante/{id}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable Long id,@RequestBody Participante participante) {
        return participanteService.update(participante);
    }

    @DeleteMapping("/deleteParticipante")
    public void deleteParticipante(Participante participante){
        participanteService.delete(participante);
    }
}
