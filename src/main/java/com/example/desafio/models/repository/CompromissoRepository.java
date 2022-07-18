package com.example.desafio.models.repository;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {

    List<Compromisso> findAllByParticipantes(Participante participante);

    List<Compromisso> findAllByParticipantes(Participante... participantes);
}
