package com.example.desafio.controllers;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import com.example.desafio.models.services.CompromissoService;
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
@RequestMapping("/compromisso")
public class CompromissoController {

    @Autowired
    CompromissoService compromissoService;

    @GetMapping
    public ResponseEntity<Page<Compromisso>> findAll(Pageable pageable) {
        return compromissoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compromisso>> findById(@PathVariable Long id) {
        return compromissoService.findById(id);
    }

    @PostMapping("/adicionarParticipante")
    public ResponseEntity<Compromisso> addParticipante(@RequestBody Compromisso participante) {
        return compromissoService.save(participante);
    }

    @PutMapping("/updateParticipante/{id}")
    public ResponseEntity<Compromisso> updateParticipante(@PathVariable Long id,@RequestBody Compromisso participante) {
        return compromissoService.update(participante);
    }

    @DeleteMapping("/deleteParticipante/{id}")
    public void deleteParticipante(@PathVariable Long id){
        compromissoService.delete(id);
    }
}
