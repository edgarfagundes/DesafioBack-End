package com.example.desafio.controllers;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.LocalidadeRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.desafio.models.enums.Situacao.PENDENTE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ParticipanteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;


    @Test
    public void testandoMetodoGeParaBuscarTodosOsParticipantes() throws Exception {
        mockMvc.perform(get("/participantes")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGeParaBuscarTodosOsCompromissoPorId() throws Exception {
        mockMvc.perform(get("/participantes/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGetParaBuscarTodosOsCompromissoPorSituacao() throws Exception {
        mockMvc.perform(get("/participantes/{id}/{situacao}", 1L,PENDENTE))
                .andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoPostParticipante() throws Exception{

        mockMvc.perform(post("/participantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Participante("Teste", 1234))))
                .andExpect(status().isCreated());
    }

    @Test
    public void testandoMetodoPutParticipante() throws Exception {
        mockMvc.perform(put("/participantes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Participante("teste", 1243432))))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testandoMetodoDeleteParticipantesPorId() throws Exception {
        mockMvc.perform(delete("/participantes/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}
