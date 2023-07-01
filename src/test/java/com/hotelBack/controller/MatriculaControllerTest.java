package com.hotelBack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hotelBack.domain.InfoMatricula.Matricula;
import com.hotelBack.service.MatriculaServicesImpl;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelBack.Mocks.matriculaComTodosDados;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MatriculaController.class)
class MatriculaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    MatriculaServicesImpl matriculaServices;
    @InjectMocks
    MatriculaController matriculaController;
    @Test
    void deveriaCadastrarComSucesso() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/matricula/matricular")
                                .content(asJsonString(matricula))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaCadastrarComFalha() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/matricula/matricular")
                                .content(asJsonString(" "))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveriaDeletarComSucesso() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/matricula/deletarMatricula/" + matricula.getId())
                                .content(asJsonString(matricula))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaDeletarComFalha() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/matricula/deletarMatricula/", " ")
                                .content(asJsonString(matricula))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveriaAtualizarComSucesso() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .put("/matricula/atualizarMatricula/" + matricula.getId())
                                .content(asJsonString(matricula))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deveriaAtualizarComFalha() throws Exception{
        Matricula matricula = matriculaComTodosDados();
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .put("/matricula/atualizarMatricula/" , " ")
                                .content(asJsonString(matricula))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();
        assertThat(mvcResult.getResponse()).isNotNull();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}