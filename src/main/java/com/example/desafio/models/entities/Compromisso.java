package com.example.desafio.models.entities;

import com.example.desafio.models.enums.Situacao;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Compromisso extends GeradorId {

    @NotNull(message = "Hora obrigatória.")
    private LocalDateTime dataHora;

    @NotNull(message = "Descrição obrigatória.")
    private String descricao;

    @NotNull(message = "Particioante(s) obrigatório(s).")
    @ManyToMany
    private List<Participante> participantes;

    @NotNull(message = "Localidade obrigatória.")
    @OneToOne
    private Localidade localidade;

    @NotNull(message = "Situação obrigatória.")
    private Situacao situacao;

    public Compromisso(LocalDateTime dataHora, String descricao, List<Participante> participantes, Localidade localidade, Situacao situacao) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.participantes = participantes;
        this.localidade = localidade;
        this.situacao = situacao;
    }

    public Compromisso() {

    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
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
