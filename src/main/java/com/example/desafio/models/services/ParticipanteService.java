package com.example.desafio.models.services;

import com.example.desafio.models.Participante;

import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    ParticipanteRepository participanteRepository;

    public ResponseEntity<Optional<Participante>> findById(Long id) {
        try {
            return new ResponseEntity(participanteRepository .findById(id), HttpStatus.OK);
        } catch (NullPointerException n) {
         return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public  ResponseEntity<Page<Participante>> findAll(Pageable pageable) {
        try {
            return new ResponseEntity(participanteRepository .findAll(pageable), HttpStatus.OK);
        }catch (NullPointerException n){
            return new ResponseEntity(n.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
