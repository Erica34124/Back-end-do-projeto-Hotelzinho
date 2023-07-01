package com.hotelBack.controller;

import com.google.gson.Gson;
import com.hotelBack.domain.InfoMatricula.Turma;
import com.hotelBack.service.TurmaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.hotelBack.Mocks.turmaComTodosDados;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TurmaController.class)
class TurmaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    TurmaServiceImpl turmaService;
    @InjectMocks
    TurmaController turmaController;

    @Test
    void deveriaCadastrarComSucesso() throws Exception{
        Turma turma = turmaComTodosDados();

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post("/turma/cadastrarTurma")
                                .content(gson.toJson(turma))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    void deveriaDeletarComSucesso() throws Exception {
        Turma turma = turmaComTodosDados();

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.delete("/turma/DeleteTurma/" + turma.getId())
                                .content(gson.toJson(turma))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaDeletarComFalha() throws Exception {
        Turma turma = turmaComTodosDados();

        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.delete("/turma/DeleteTurma/" , " ")
                                .content(gson.toJson(turma))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}