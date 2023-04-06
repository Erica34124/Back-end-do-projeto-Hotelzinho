package com.hotelBack.service;

import com.hotelBack.domain.InfoMatricula.Matricula;


public interface MatriculaService {
    public Matricula matricularAluno(Matricula matricula);
    public void alterarMatriculaAluno(String id);
    public void deletarMatricula(String id);

}
