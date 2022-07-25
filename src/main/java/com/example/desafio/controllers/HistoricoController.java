package com.example.desafio.controllers;

import com.example.desafio.models.entities.Historico;
import com.example.desafio.models.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    HistoricoService historicoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Historico> findAll(Pageable pageable) {
        return historicoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Historico> findById(@PathVariable Long id) {
        return historicoService.findById(id);
    }

    @GetMapping("/compromisso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Historico findHistoricoCompromisso(@PathVariable Long id){
        return historicoService.findHistorcoCompromisso(id);
    }

}
