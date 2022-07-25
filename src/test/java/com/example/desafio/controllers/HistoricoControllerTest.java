package com.example.desafio.controllers;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Historico;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.HistoricoRepository;
import com.example.desafio.models.repository.LocalidadeRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HistoricoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testandoMetodoGeParaBuscarTodosOsHistoricos() throws Exception {
        mockMvc.perform(get("/historicos")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGeParaBuscarTodosOsHistoricosPorId() throws Exception {
        mockMvc.perform(get("/historicos/{id}", 1L))
                .andExpect(status().isOk());
    }
}
