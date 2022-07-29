package com.example.desafio.models.repository;

import com.example.desafio.models.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Participante findAllById(Long id);
}
