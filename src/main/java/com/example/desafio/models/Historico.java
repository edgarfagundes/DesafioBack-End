package com.example.desafio.models;

import com.example.desafio.models.enums.Situacao;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Historico extends GeradorId{

    @NotNull
    @ManyToOne
    Compromisso compromisso;

    @NotNull
    private Situacao situacao;

    @NotNull
    Date data;

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
