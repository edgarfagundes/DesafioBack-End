package com.example.desafio.controllers;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import com.example.desafio.models.services.CompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {

    @Autowired
    CompromissoService compromissoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Compromisso> findAll(Pageable pageable) {
        return compromissoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Compromisso> findById(@PathVariable Long id) {
        return compromissoService.findById(id);
    }

    @PostMapping("/adicionarCompromisso")
    @ResponseStatus(HttpStatus.CREATED)
    public Compromisso addCompromisso(@RequestBody Compromisso compromisso, Participante participante) {
        return compromissoService.save(compromisso, participante);
    }

    @PutMapping("/updateCompromisso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Compromisso updateCompromisso(@PathVariable Long id, @RequestBody Compromisso compromisso) {
        return compromissoService.update(id, compromisso);
    }

    @DeleteMapping("/deleteCompromisso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        compromissoService.delete(id);
    }
}
