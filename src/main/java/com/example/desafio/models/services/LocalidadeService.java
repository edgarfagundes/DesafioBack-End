package com.example.desafio.models.services;

import com.example.desafio.models.Localidade;
import com.example.desafio.models.Participante;
import com.example.desafio.models.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    public ResponseEntity<Optional<Localidade>> findById(Long id) {
        try {
            return new ResponseEntity(localidadeRepository.findById(id), HttpStatus.OK);
        } catch (NullPointerException n) {
            return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Page<Localidade>> findAll(Pageable pageable) {
        try {
            return new ResponseEntity(localidadeRepository.findAll(pageable), HttpStatus.OK);
        } catch (NullPointerException n) {
            return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Localidade> save(Localidade participante) {
        try {
            return new ResponseEntity(localidadeRepository.save(participante), HttpStatus.ACCEPTED);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public ResponseEntity<Localidade> update(Localidade localidade) {
        try {
            return new ResponseEntity(localidadeRepository.save(localidade), HttpStatus.ACCEPTED);

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public void delete(Localidade localidade) {
        if (localidade.equals(null)){
            throw new NullPointerException();
        } else {
            localidadeRepository.delete(localidade);
        }
    }
}
