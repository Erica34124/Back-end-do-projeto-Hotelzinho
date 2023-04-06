package com.hotelBack.controller;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.service.AlunoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    AlunoServicesImpl alunoServices;

    @PostMapping("/cadastrar")
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return alunoServices.cadastrarAluno(aluno);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable(name = "id") String id) {
        alunoServices.deletarAluno(id);
    }
    @PutMapping("/atualizar/{id}")
    public Optional<Aluno> atualizar(@PathVariable(name = "id") String id, @RequestBody Aluno aluno){
        return alunoServices.alterarCadastroAluno(id, aluno);
    }
    @GetMapping("/buscarTodos")
    public List<Aluno> buscarTodos(){
        return alunoServices.buscarTodosAlunos();
    }

    }
