package com.hotelBack;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoAluno.Telefone;

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

}
