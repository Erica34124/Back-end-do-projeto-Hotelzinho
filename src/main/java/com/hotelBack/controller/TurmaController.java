package com.hotelBack.controller;

import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.service.TurmaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/turma")
public class TurmaController {
    @Autowired
    TurmaServiceImpl turmaService;

    @PostMapping("/cadastrarTurma")
        public Turma cadastrar(@RequestBody Turma turma){
            return turmaService.cadastrarTurma(turma);
    }
    @DeleteMapping("/DeleteTurma/{id}")
    public void deletar(@PathVariable(name = "id")String id){
        turmaService.deletarTurma(id);
    }
}
