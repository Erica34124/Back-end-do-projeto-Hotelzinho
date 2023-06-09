package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoMatricula.Matricula;

import java.util.Optional;


public interface MatriculaService {
    public Matricula matricularAluno(Matricula matricula);
    public Optional<Matricula> alterarMatriculaAluno(String id, Matricula matricula);
    public void deletarMatricula(String id);
    public void deletarAlunoNaTurma(String idTurma, String idAluno);
    public void adicionarMatriculaNaTurma(Aluno alunos, String idTurma);

}
