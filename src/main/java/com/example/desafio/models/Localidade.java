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
