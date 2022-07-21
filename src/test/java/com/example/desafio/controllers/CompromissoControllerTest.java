package com.example.desafio.controllers;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Localidade;
import com.example.desafio.models.entities.Participante;
import com.example.desafio.models.enums.Situacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompromissoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testandoMetodoGeParaBuscarTodosOsCompromissos() throws Exception {
        mockMvc.perform(get("/compromissos")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGeParaBuscarTodosOsCompromissoPorId() throws Exception {
        mockMvc.perform(get("/compromissos/{id}", 1L))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testandoMetodoPostCompromisso() throws Exception{
//        List<Participante> participantes = new ArrayList<>();
//        Participante participante = new Participante("edgar", "448484");
//        participantes.add(participante);
//        Localidade localidade = new Localidade("Olá", "vmskdvmsv", "kdvmskdvmksd");
//        Compromisso compromisso = new Compromisso(LocalDateTime.now(), "Ola", participantes, localidade , Situacao.PENDENTE);
//        mockMvc.perform(post("/compromissos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(compromisso)))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    public void testandoMetodoDeleteCompromissoPorId() throws Exception {
//        mockMvc.perform(delete("/compromissos/{id}", 1L))
//                .andExpect(status().isNoContent());
//    }
//
//
}
