package com.hotelBack;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoAluno.Telefone;
import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.domain.InfoMatricula.Turma;

import java.util.ArrayList;
import java.util.List;

public class Mocks {
    public static Aluno alunosComTodosDados(){
        Aluno aluno = new Aluno();
        aluno.setNome("Adriana");
        aluno.setResponsavel("Joao");
        aluno.setId("123");
        aluno.setNascimento("10/10/1998");
        aluno.setTelefone(new Telefone());
        aluno.setEmail("adriana@gmail.com");
        aluno.setConvenio("Bradesco");

        return aluno;
    }

    public static List<Aluno> listaAlunosComTodosDados(){
        List<Aluno> lista = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            lista.add(alunosComTodosDados());
        }


        return lista;
    }

    public static Matricula matriculaComTodosDados(){
        Matricula matricula = new Matricula();
        matricula.setRa("4636");
        matricula.setId("654647");
        matricula.setIdTurma("5675856");
        matricula.setMatriculado(true);
        matricula.setIdAluno("454");
        matricula.setAno("2023");

        return matricula;
    }

    public static Turma turmaComTodosDados(){
        Turma turma = new Turma();
        turma.setId("4636");
        turma.setQuantidadeMaxima(10);
        turma.setAluno(new ArrayList<>());
        turma.setSerie(2);
        turma.setNome("Prescila");

        return turma;
    }
}
