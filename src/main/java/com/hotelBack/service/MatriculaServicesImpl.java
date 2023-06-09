package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.repository.AlunoRepository;
import com.hotelBack.repository.MatriculaRepository;
import com.hotelBack.repository.TurmaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MatriculaServicesImpl implements MatriculaService {
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    TurmaRepository turmaRepository;

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public Matricula matricularAluno(Matricula matricula) {
        Optional<Aluno> aluno = alunoRepository.findById(matricula.getIdAluno());
        if (aluno.isPresent()) {
            matricula.setRa(UUID.randomUUID().toString());
            adicionarMatriculaNaTurma(aluno.get(), matricula.getIdTurma());
            return matriculaRepository.save(matricula);
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
    }

    @Override
    public Optional<Matricula> alterarMatriculaAluno(String id, Matricula matricula) {
        Optional<Matricula> matriculado = matriculaRepository.findById(id);
        if (matriculado.isPresent()) {
            Matricula mat = matriculado.get();
            mat.setIdTurma(matricula.getIdTurma());
            mat.setAno(matricula.getAno());
            logger.info("Matricula atualizada com sucessso! ");
            return Optional.of(matriculaRepository.save(mat));
        }
        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Matricula não encontrada");
    }

    @Override
    public void deletarMatricula(String id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);

        if (matricula.isPresent()) {
            matriculaRepository.deleteById(id);
            deletarAlunoNaTurma(matricula.get().getIdTurma(), matricula.get().getIdAluno());
            logger.info("Matricula deletada com sucessso! ");
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Matricula não encontrado");
        }

    }

    @Override
    public void deletarAlunoNaTurma(String idTurma, String idAluno) {
        Optional<Turma> turma = turmaRepository.findById(idTurma);

        if (turma.isPresent()) {
            AtomicReference<Integer> quantidade = new AtomicReference<>(turma.get().getQuantidadeAlunos());

            List<Aluno> alunos = turma.get().getAluno();

            alunos.forEach(e -> {
                if (e.getId().equals(idAluno)) {
                    alunos.remove(e);
                    turma.get().setQuantidadeAlunos(quantidade.updateAndGet(v -> v - 1));
                    turmaRepository.save(turma.get());
                }
            });
        }
    }

    @Override
    public void adicionarMatriculaNaTurma(Aluno alunos, String idTurma) {
        Optional<Turma> turma = turmaRepository.findById(idTurma);
        Optional<Aluno> aluno = alunoRepository.findById(alunos.getId());
        Integer quantidade = turma.get().getQuantidadeAlunos();

        if (turma.isPresent()) {
            if (turma.get().getQuantidadeAlunos() < turma.get().getQuantidadeMaxima()) {
                turma.get().setQuantidadeAlunos(quantidade += 1);
                turma.get().getAluno().add(aluno.get());
                turmaRepository.save(turma.get());
            } else {
                throw new ResponseStatusException
                        (HttpStatus.BAD_REQUEST, "Turma completa. ");
            }
        }
    }
}
