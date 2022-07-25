package com.example.desafio.models.entities;

import com.example.desafio.models.enums.Situacao;
import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Historico extends GeradorId{


    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Compromisso compromisso;

    @NotNull
    private Situacao situacao;

    @NotNull
    private LocalDateTime data;

    public Historico(Compromisso compromisso, Situacao situacao, LocalDateTime data) {
        this.compromisso = compromisso;
        this.situacao = situacao;
        this.data = data;
    }

    public Historico() {
    }

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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
