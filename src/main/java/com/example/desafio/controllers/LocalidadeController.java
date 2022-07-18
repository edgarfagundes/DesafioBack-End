package com.example.desafio.controllers;

import com.example.desafio.models.Localidade;
import com.example.desafio.models.Localidade;
import com.example.desafio.models.services.LocalidadeService;
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
@RequestMapping("/localidade")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;

    @GetMapping
    public Page<Localidade> findAll(Pageable pageable) {
        return localidadeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Localidade> findById(@PathVariable Long id) {
        return localidadeService.findById(id);
    }

    @PostMapping("/adicionarLocalidade")
    public Localidade addLocalidade(@RequestBody Localidade localidade) {
        return localidadeService.save(localidade);
    }

    @PutMapping("/updateLocalidade/{id}")
    public Localidade updateLocalidade(@PathVariable Long id,@RequestBody Localidade localidade) {
        return localidadeService.update(localidade);
    }

    @DeleteMapping("/deleteLocalidade")
    public void deleteLocalidade(Localidade localidade){
        localidadeService.delete(localidade);
    }

}
