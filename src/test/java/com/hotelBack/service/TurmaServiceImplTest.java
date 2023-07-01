package com.hotelBack.service;

import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.repository.TurmaRepository;
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

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TurmaServiceImplTest {
    @Mock
    TurmaRepository turmaRepository;
    @InjectMocks
    TurmaServiceImpl turmaServiceImpl;

    private Turma turma;

    @BeforeEach
    public void init(){
        EasyRandom tumaRandom = new EasyRandom();
        turma = tumaRandom.nextObject(Turma.class);
        turma.setId("123");
    }
    @Test
    void deveriaCadastrarTurma() {

        //Action
        when(turmaRepository.save(turma)).thenReturn(turma);
        turmaServiceImpl.cadastrarTurma(turma);

        //Assert
        Assertions.assertEquals("123", turma.getId());
    }

    @Test
    void deveriaDeletarTurma() {

        when(turmaRepository.findById(turma.getId())).thenReturn(Optional.of(turma));
        turmaServiceImpl.deletarTurma(turma.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidCpfParameters")
    void deveriaDeletarTurmaComFalha(String id) {
    Assertions.assertThrows(ResponseStatusException.class, () -> turmaServiceImpl.deletarTurma(id));
    }

    private static Stream<String> invalidCpfParameters() {
        return Stream.of(null, "45y498", "");
    }
}