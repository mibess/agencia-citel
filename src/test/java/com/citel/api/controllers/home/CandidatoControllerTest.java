package com.citel.api.controllers.home;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citel.api.controllers.candidato.CandidatoController;
import com.citel.api.http.dto.ImportacaoCompletaDTO;
import com.citel.api.services.candidato.CandidatoService;

@ExtendWith(MockitoExtension.class)
class CandidatoControllerTest {

  @InjectMocks
  private CandidatoController candidatoController;

  @Mock
  private CandidatoService candidatoService;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(candidatoController).build();
  }

  @Test
  @DisplayName(value = "Deve retornar 200 ok")
  void shouldReturnOKWhenImportAFile() throws Exception {

    ImportacaoCompletaDTO importacaoCompletaDTO = new ImportacaoCompletaDTO(1L, "Ok");

    when(candidatoService.salvarLista(Mockito.anyList()))
        .thenReturn(importacaoCompletaDTO);

    mockMvc.perform(MockMvcRequestBuilders.get("/candidatos/importar")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

  }

}
