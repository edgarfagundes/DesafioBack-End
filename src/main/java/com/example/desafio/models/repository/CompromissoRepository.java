package com.example.desafio.models.repository;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {

    List<Compromisso> findAllByParticipantes(Participante participante);

    List<Compromisso> findAllByLocalidade(Localidade localidade);

    List<Compromisso> findAllByParticipantesIn(List<Participante> participantes);

}
