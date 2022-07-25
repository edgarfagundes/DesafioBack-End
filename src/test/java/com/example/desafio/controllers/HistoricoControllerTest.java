package com.example.desafio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


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
