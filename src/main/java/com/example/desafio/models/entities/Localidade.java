package com.example.desafio.models.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Localidade extends GeradorId {

    public Localidade(String nome, String longitude, String latitude) {
        this.nome = nome;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Localidade() {

    }

    @NotNull(message = "Nome obrigatório.")
    private String nome;

    private String longitude;

    private String latitude;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
