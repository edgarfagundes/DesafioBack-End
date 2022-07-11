package com.example.desafio.models.repository;

import com.example.desafio.models.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {
}
