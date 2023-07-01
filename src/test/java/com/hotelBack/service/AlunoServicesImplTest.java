package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.repository.AlunoRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServicesImplTest {
@Mock
AlunoRepository alunoRepository;
@InjectMocks
AlunoServicesImpl alunoServiceImpl;

private Aluno aluno;

@BeforeEach
public void init(){
    EasyRandom generation = new EasyRandom();
    aluno = generation.nextObject(Aluno.class);
    aluno.setId("1233");
}

    @Test
    void deveriaCadastrarAluno() {

        when(alunoRepository.save(aluno)).thenReturn(aluno);
        alunoServiceImpl.cadastrarAluno(aluno);

        Assertions.assertEquals(aluno.getId(), "1233");
    }

    @Test
    void deveriaAlterarCadastroAluno() {

        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(aluno)).thenReturn((aluno));

       alunoServiceImpl.alterarCadastroAluno(aluno.getId(), aluno);
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaAlterarCadastroAlunoComFalha(String id) {

        Assertions.assertThrows(ResponseStatusException.class, () -> alunoServiceImpl.alterarCadastroAluno(id, aluno));
    }

    @Test
    void deveriaDeletarAluno() {

        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        alunoRepository.deleteById(aluno.getId());

        alunoServiceImpl.deletarAluno(aluno.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaDeletarAlun0ComFalha(String id){
        Assertions.assertThrows(ResponseStatusException.class, () -> alunoServiceImpl.deletarAluno(id));
    }

    @Test
    void deveriaBuscarTodosAlunos() {

        List<Aluno> alunos = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            alunos.add(aluno);
        }
        when(alunoRepository.findAll()).thenReturn(alunos);
        alunoServiceImpl.buscarTodosAlunos();
    }

    @Test
    void deveriaBuscarAlunoPorId() {

        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        alunoServiceImpl.buscarAlunoPorId(aluno.getId());

        Assertions.assertEquals("1233", aluno.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaBuscarAlunoPorIdComFalha(String id) {
     Assertions.assertThrows(ResponseStatusException.class, () -> alunoServiceImpl.buscarAlunoPorId(id));
    }

    private static Stream<String> invalidCpfParameters() {
        return Stream.of(null, "45y498", "");
    }
}