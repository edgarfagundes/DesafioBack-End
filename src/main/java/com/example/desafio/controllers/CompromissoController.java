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

import javax.naming.NoPermissionException;
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

    @PostMapping("/adicionarComprimisso")
    public ResponseEntity<Compromisso> addCompromisso(@RequestBody Compromisso compromisso) {
        return compromissoService.save(compromisso);
    }

    @PutMapping("/updateCompromisso/{id}")
    public ResponseEntity<Compromisso> updateCompromisso(@PathVariable Long id,@RequestBody Compromisso compromisso) {
        return compromissoService.update(compromisso);
    }

    @DeleteMapping("/deleteCompromisso/{id}")
    public void delete(@PathVariable Long id, @RequestBody Compromisso compromisso) {
        compromissoService.delete(compromisso);
    }
}
