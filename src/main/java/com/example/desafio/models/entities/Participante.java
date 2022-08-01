package com.example.desafio.models.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Participante extends GeradorId {

    @NotNull(message = "Nome obrigatório.")
    private String nome;

    @NotNull(message = "Telefone obrigatório.")
    private Integer telefone;

    public Participante(String nome, Integer telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Participante() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
}
