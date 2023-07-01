package com.hotelBack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hotelBack.domain.InfoAluno.Aluno;
import com.hotelBack.service.AlunoServicesImpl;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.hotelBack.Mocks.alunosComTodosDados;
import static com.hotelBack.Mocks.listaAlunosComTodosDados;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlunoController.class)
class AlunoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private Gson gson;
    @MockBean
    AlunoServicesImpl alunoServices;
    @InjectMocks
    AlunoController alunoController;

    @Test
    void deveriaCadastrarComSucesso() throws Exception {
        Aluno aluno = alunosComTodosDados();

        var result =
                mvc.perform(MockMvcRequestBuilders.post("/aluno/cadastrar")
                                .content(asJsonString(aluno))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaCadastrarComFalha() throws Exception {

        var result =
                mvc.perform(MockMvcRequestBuilders.post("/aluno/cadastrar")
                                .content(asJsonString(null))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveriaDeletarComSucesso() throws Exception {
        Aluno aluno = alunosComTodosDados();

        var result =
                mvc.perform(MockMvcRequestBuilders.delete("/aluno/deletar/" + aluno.getId())
                                .content(asJsonString(null))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaDeletarComFalha() throws Exception {

        var result =
                mvc.perform(MockMvcRequestBuilders.delete("/aluno/deletar/", " ")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveriaAtualizarComSucesso() throws Exception {
        Aluno aluno = alunosComTodosDados();
        var result =
                mvc.perform(MockMvcRequestBuilders.put("/aluno/atualizar/" + aluno.getId())
                                .content(asJsonString(aluno))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaAtualizarComFalha() throws Exception {
        Aluno aluno = alunosComTodosDados();
        var result =
                mvc.perform(MockMvcRequestBuilders.put("/aluno/atualizar/" , " ")
                                .content(asJsonString(aluno))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    @Test
    void deveriaBuscarTodosComSucesso() throws Exception {

        List<Aluno> lista = listaAlunosComTodosDados();
        var result =
                mvc.perform(MockMvcRequestBuilders.get("/aluno/buscarTodos")
                                .content(asJsonString(lista))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaBuscarAlunoPorIdComSucesso() throws Exception {
        Aluno aluno = alunosComTodosDados();
        var result =
                mvc.perform(MockMvcRequestBuilders.get("/aluno/buscarAlunoPorId/" + aluno.getId())
                                .content(asJsonString(aluno))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaBuscarAlunoPorIdComFalha() throws Exception {
        Aluno aluno = alunosComTodosDados();
        var result =
                mvc.perform(MockMvcRequestBuilders.get("/aluno/buscarAlunoPorId/" , " ")
                                .content(asJsonString(aluno))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();

        org.assertj.core.api.Assertions.assertThat(result.getResponse()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}