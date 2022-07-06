package com.example.desafio.models;

import com.example.desafio.models.enums.Situacao;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Compromisso extends GeradorId {

    @NotNull
    private Date dataHora;

    @NotNull
    private String descricao;

    @NotNull
    @ManyToMany
    private List<Participante> participantes;

    @NotNull
    @OneToOne
    private Localidade localidade;

    @NotNull
    private Situacao situacao;


    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
