package com.example.desafio.models.repository;

import com.example.desafio.models.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    Historico findAllByCompromisso_Id(Long id);
}
