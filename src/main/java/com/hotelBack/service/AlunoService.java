package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    public Aluno cadastrarAluno(Aluno aluno);
    public Optional<Aluno> alterarCadastroAluno(String id, Aluno alunoRequest);
    public void deletarAluno(String id);
    public List<Aluno> buscarTodosAlunos();

}
