package com.example.desafio.models.services;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Compromisso;
import com.example.desafio.models.repository.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CompromissoService {

    @Autowired
    CompromissoRepository compromissoRepository;

    public ResponseEntity<Optional<Compromisso>> findById(Long id) {
        try {
            return new ResponseEntity(compromissoRepository.findById(id), HttpStatus.OK);
        } catch (NullPointerException n) {
            return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Page<Compromisso>> findAll(Pageable pageable) {
        try {
            return new ResponseEntity(compromissoRepository.findAll(pageable), HttpStatus.OK);
        } catch (NullPointerException n) {
            return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Compromisso> save(Compromisso Compromisso) {
        try {
            return new ResponseEntity(compromissoRepository.save(Compromisso), HttpStatus.ACCEPTED);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public ResponseEntity<Compromisso> update(Compromisso Compromisso) {
        try {
            return new ResponseEntity(compromissoRepository.save(Compromisso), HttpStatus.ACCEPTED);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Long id) {
        Optional<Compromisso> compromisso = compromissoRepository.findById(id);
        if (!(compromissoRepository.existsById(id))){
            throw new NullPointerException();
        } else {
            compromissoRepository.delete(compromisso);
        }
    }
}
