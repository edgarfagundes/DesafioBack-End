package com.example.desafio.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Locale;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Localidade extends GeradorId{


    @NotNull
    private String nome;

    private Locale longitude;

    private Locale latitude;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Locale getLongitude() {
        return longitude;
    }

    public void setLongitude(Locale longitude) {
        this.longitude = longitude;
    }

    public Locale getLatitude() {
        return latitude;
    }

    public void setLatitude(Locale latitude) {
        this.latitude = latitude;
    }
}
