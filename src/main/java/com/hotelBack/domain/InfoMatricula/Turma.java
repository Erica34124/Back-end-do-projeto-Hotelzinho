package com.hotelBack.domain.InfoMatricula;

import com.hotelBack.domain.InfoAluno.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {
    private String id;
    private String nome;
    private Integer serie;
    private Integer quantidadeAlunos;
    private Integer quantidadeMaxima;
    private List<Aluno> aluno = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turma turma)) return false;
        return Objects.equals(getId(), turma.getId()) && Objects.equals(getNome(), turma.getNome()) && Objects.equals(getSerie(), turma.getSerie()) && Objects.equals(getQuantidadeAlunos(), turma.getQuantidadeAlunos()) && Objects.equals(getQuantidadeMaxima(), turma.getQuantidadeMaxima()) && Objects.equals(getAluno(), turma.getAluno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getSerie(), getQuantidadeAlunos(), getQuantidadeMaxima(), getAluno());
    }
}
