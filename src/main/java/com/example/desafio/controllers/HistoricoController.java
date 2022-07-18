package com.example.desafio.controllers;

import com.example.desafio.models.Historico;
import com.example.desafio.models.Localidade;
import com.example.desafio.models.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    HistoricoService historicoService;

    @GetMapping
    public Page<Historico> findAll(Pageable pageable) {
        return historicoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Historico> findById(@PathVariable Long id) {
        return historicoService.findById(id);
    }

}
