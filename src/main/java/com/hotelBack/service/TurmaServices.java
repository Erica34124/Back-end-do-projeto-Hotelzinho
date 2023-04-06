package com.hotelBack.service;

import com.hotelBack.domain.InfoMatricula.Turma;
import org.springframework.stereotype.Service;

@Service
public interface TurmaServices {
    Turma cadastrarTurma(Turma turma);
    void deletarTurma(String id);

}
