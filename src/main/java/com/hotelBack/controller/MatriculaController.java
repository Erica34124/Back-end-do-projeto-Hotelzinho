package com.hotelBack.controller;

import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.service.MatriculaServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    MatriculaServicesImpl matriculaServices;

    @PostMapping("/matricular")
    public Matricula cadastrar( @RequestBody Matricula matricula) {
        return matriculaServices.matricularAluno(matricula);
    }

    @DeleteMapping("/deletarMatricula/{id}")
    public void deletar(@PathVariable(name = "id") String id) {
        matriculaServices.deletarMatricula(id);
    }
    @PutMapping("/atualizarMatricula/{id}")
    public void atualizar(@PathVariable(name = "id") String id){
        matriculaServices.alterarMatriculaAluno(id);
    }


    }
