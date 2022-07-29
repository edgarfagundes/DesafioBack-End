package com.example.desafio.controllers;

import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.services.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/localidades")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Localidade> findAll(Pageable pageable) {
        return localidadeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Localidade> findById(@PathVariable Long id) {
        return localidadeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Localidade addLocalidade(@RequestBody Localidade localidade) {
        return localidadeService.save(localidade);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Localidade updateLocalidade(@PathVariable Long id,@RequestBody Localidade localidade) {
        return localidadeService.update(id, localidade);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteLocalidade(@PathVariable Long id){
        localidadeService.delete(id);
    }

}
