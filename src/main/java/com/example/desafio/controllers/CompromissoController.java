package com.example.desafio.controllers;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.services.CompromissoService;
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

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/compromissos")
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Compromisso addCompromisso(@RequestBody Compromisso compromisso) {
        return compromissoService.save(compromisso);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void updateCompromisso(@PathVariable Long id, @RequestBody Compromisso compromisso) {
        compromissoService.update(id, compromisso);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id) {
        compromissoService.deleteById(id);
    }
}
