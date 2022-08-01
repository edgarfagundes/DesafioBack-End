package com.example.desafio.controllers;

import com.example.desafio.models.entities.Localidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocalidadeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testandoMetodoGetParaBuscarTodasAsLocalidades() throws Exception {
        mockMvc.perform(get("/localidades")).andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoGetParaBuscarTodasAsLocalidadePorId() throws Exception {
        mockMvc.perform(get("/localidades/{id}", 6))
                .andExpect(status().isOk());
    }

    @Test
    public void testandoMetodoPostLocalidade() throws Exception {

        mockMvc.perform(post("/localidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Localidade("teste", "teste2", "teste3"))))
                .andExpect(status().isCreated());
    }

    @Test
    public void testandoMetodoPutLocalidade() throws Exception {

        mockMvc.perform(put("/localidades/{id}", 6)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Localidade("teste", "teste2", "teste3"))))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testandoMetodoDeleteLocalidadePorId() throws Exception {
        mockMvc.perform(delete("/localidades/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
