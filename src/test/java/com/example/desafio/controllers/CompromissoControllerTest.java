package com.example.desafio.controllers;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.LocalidadeRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompromissoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    CompromissoRepository compromissoRepository;


    @Test
    public void testandoMetodoGetParaBuscarTodosOsCompromissos() throws Exception {
        mockMvc.perform(get("/compromissos")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGeParaBuscarTodosOsCompromissoPorId() throws Exception {
        mockMvc.perform(get("/compromissos/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoPostCompromisso() throws Exception {
        Participante participante = new Participante("Edgar", 524534634);
        participanteRepository.save(participante);

        Localidade localidade = new Localidade("Teste", "dfsddgs", "vdvsdbsbs");
        localidadeRepository.save(localidade);

        mockMvc.perform(post("/compromissos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Compromisso(LocalDateTime.now(), "teste", List.of(participante), localidade, Situacao.PENDENTE))))
                .andExpect(status().isCreated());
    }

    @Test
    public void testandoMetodoPutCompromisso() throws Exception {
        Participante participante = new Participante("Edgar", 524534634);
        participanteRepository.save(participante);

        Localidade localidade = new Localidade("Teste", "dfsddgs", "vdvsdbsbs");
        localidadeRepository.save(localidade);

        mockMvc.perform(put("/compromissos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Compromisso(LocalDateTime.now(), "Olá", List.of(participante), localidade, Situacao.PENDENTE))))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testandoMetodoDeleteCompromissoPorId() throws Exception {
        mockMvc.perform(delete("/compromissos/{id}", 1L))
                .andExpect(status().isNoContent());
    }


}
