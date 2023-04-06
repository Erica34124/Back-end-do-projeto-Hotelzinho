package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.repository.AlunoRepository;
import com.hotelBack.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class MatriculaServicesImpl implements MatriculaService {
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    MatriculaRepository matriculaRepository;

    @Override
    public Matricula matricularAluno(Matricula matricula) {
        Optional<Aluno> aluno = alunoRepository.findById(matricula.getIdAluno());
        if (aluno.isPresent()) {
            matricula.setRa(UUID.randomUUID().toString());
            adicionarTurma(matricula.getIdAluno());
            return matriculaRepository.save(matricula);
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
    }

    @Override
    public void alterarMatriculaAluno(String id) {
    }

    @Override
    public void deletarMatricula(String id) {

    }
    public void adicionarTurma(String id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        Turma turma = new Turma();
        if (turma.getQuantidadeAlunos() <= turma.getQuantidadeMaxima()){
            aluno.get().getNome();
            Aluno ana = aluno.get();
            turma.setAluno(ana);
            Integer quantidade = turma.getQuantidadeAlunos();
            turma.setQuantidadeAlunos(quantidade += 1);


        }

    }
}
