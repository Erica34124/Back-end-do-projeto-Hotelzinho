package com.hotelBack.service;

import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.repository.AlunoRepository;
import com.hotelBack.repository.MatriculaRepository;
import com.hotelBack.repository.TurmaRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MatriculaServicesImplTest {
    @Mock
    AlunoRepository alunoRepository;
    @Mock
    MatriculaRepository matriculaRepository;

    @Mock
    TurmaRepository turmaRepository;
    @InjectMocks
    MatriculaServicesImpl matriculaServicesImpl;

    private Aluno aluno;
    private Turma turma;
    private Matricula matricula;

    @BeforeEach
    public void init(){
        EasyRandom matriculaRandom = new EasyRandom();
        matricula = matriculaRandom.nextObject(Matricula.class);
        matricula.setIdAluno("12324");
        matricula.setId("321");

        EasyRandom generationAluno = new EasyRandom();
        aluno = generationAluno.nextObject(Aluno.class);
        aluno.setId("12324");

        EasyRandom generationTurma= new EasyRandom();
        turma = generationAluno.nextObject(Turma.class);
        turma.setId("456");
        turma.setQuantidadeAlunos(1);
        turma.setQuantidadeMaxima(5);

    }


    @Test
    void deveriaMatricularAluno() {

        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(matriculaRepository.save(matricula)).thenReturn(matricula);
        when(turmaRepository.findById(matricula.getIdTurma())).thenReturn(Optional.of(turma));
        when(turmaRepository.save(turma)).thenReturn((turma));
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));


        matriculaServicesImpl.adicionarMatriculaNaTurma(aluno, matricula.getIdTurma());
        matriculaServicesImpl.matricularAluno(matricula);
    }

    @Test
    void deveriaAlterarMatriculaAluno() {

        when(matriculaRepository.findById(matricula.getId())).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(matricula)).thenReturn(matricula);

        matriculaServicesImpl.alterarMatriculaAluno(matricula.getId(), matricula);
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaalterarMatriculaAlunoComFalha(String id) {

        when(matriculaRepository.findById(id)).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(matricula)).thenReturn(matricula);

        matriculaServicesImpl.alterarMatriculaAluno(id, matricula);
    }

    @Test
    void deveriaDeletarMatricula() {

        matriculaRepository.deleteById(matricula.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaDeletarMatriculaComFalha(String id) {

        matriculaRepository.deleteById(id);
    }

    @Test
    void deveriaDeletarAlunoNaTurma() {

        when(turmaRepository.findById(turma.getId())).thenReturn(Optional.of(turma));

        matriculaServicesImpl.deletarAlunoNaTurma(turma.getId(), "123");

    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaDeletarAlunoNaTurmaComFalha(String id) {

        when(turmaRepository.findById(turma.getId())).thenReturn(Optional.of(turma));

        matriculaServicesImpl.deletarAlunoNaTurma(turma.getId(), id);
    }

    @Test
    void deveriaAdicionarMatriculaNaTurma() {

        when(turmaRepository.findById(turma.getId())).thenReturn(Optional.of(turma));
        when(turmaRepository.save(turma)).thenReturn((turma));
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));

        matriculaServicesImpl.adicionarMatriculaNaTurma(aluno, turma.getId());
    }

    private static Stream<String> invalidCpfParameters() {
        return Stream.of(null, "45y498", "");
    }
}