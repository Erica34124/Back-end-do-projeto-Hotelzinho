package com.hotelBack.service;

import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class TurmaServiceImpl implements TurmaServices{
    @Autowired
    TurmaRepository turmaRepository;
    @Override
    public Turma cadastrarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public void deletarTurma(String id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        if (turma.isPresent()) {
            turmaRepository.deleteById(id);
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Turma não encontrada");
        }
    }
}
