package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService service;

    @DisplayName("Deveria retornar o código 400 para solicitações de adoção com erros")
    @Test
    void cenarioCodigo400() throws Exception {
        //ARRANGE

        String json = "{}";

        //ACT

        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT

        assertEquals(400, response.getStatus());
    }


    @DisplayName("Deveria retornar o código 200 para solicitações de adoção que não apresenta erro")
    @Test
    void cenarioCodigo200() throws Exception {
        //ARRANGE

        String json = """
                {
                    "idPet":1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"               
                }
                """;


        //ACT

        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT

        assertEquals(200, response.getStatus());
    }
}