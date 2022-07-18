package com.example.desafio.models.repository;

import com.example.desafio.models.Compromisso;
import com.example.desafio.models.Localidade;
import com.example.desafio.models.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {

    List<Compromisso> findAllByParticipantes(Participante participante);

    List<Compromisso> findAllByParticipantes(Participante... participantes);

    List<Compromisso> findAllByLocalidade(Localidade localidade);
}
