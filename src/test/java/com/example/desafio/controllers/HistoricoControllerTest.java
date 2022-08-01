package com.example.desafio.controllers;

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

    @Test
    public void testandoMetodoGetParaBuscarTodosOsHistoricos() throws Exception {
        mockMvc.perform(get("/historicos")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGetParaBuscarTodosOsHistoricosPorId() throws Exception {
        mockMvc.perform(get("/historicos/{id}", 210))
                .andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGetParaBuscarTodosOsHistoricosPorCompromisso() throws Exception {
        mockMvc.perform(get("/historicos/compromisso/{id}", 1L))
                .andExpect(status().isOk());
    }
}
