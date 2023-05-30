package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.repository.AlunoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class AlunoServicesImpl implements AlunoService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Optional<Aluno> alterarCadastroAluno(String id, Aluno alunoRequest) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            aluno.get().setNome(alunoRequest.getNome());
            aluno.get().setNascimento(alunoRequest.getNascimento());
            aluno.get().setEndereco(alunoRequest.getEndereco());
            aluno.get().setTelefone(alunoRequest.getTelefone());
            aluno.get().setResponsavel(alunoRequest.getResponsavel());
            aluno.get().setEmail(alunoRequest.getEmail());
            aluno.get().setConvenio(alunoRequest.getConvenio());
            logger.info("Aluno atualizado com sucessso! ");
            return Optional.of(alunoRepository.save(aluno.get()));
        }

        throw new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Aluno não encontrado");
    }

    @Override
    public void deletarAluno(String id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            alunoRepository.deleteById(id);
            logger.info("Aluno deletado com sucessso! ");
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }

    }

    @Override
    public List<Aluno> buscarTodosAlunos() {
        List<Aluno> todosAlunos = alunoRepository.findAll();
        return todosAlunos;
    }

    @Override
    public Optional<Aluno> buscarAlunoPorId(String id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            logger.info("Aluno Atualizado com sucessso! ");
            return aluno;
        } else {
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
    }
}
