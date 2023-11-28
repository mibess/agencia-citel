package com.citel.api.controllers.candidato;

import static org.mockito.Mockito.when;

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

import com.citel.api.app.controllers.candidato.CandidatoController;
import com.citel.api.domain.candidato.dto.ImportacaoCompletaDTO;
import com.citel.api.domain.candidato.services.CandidatoService;

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
  @DisplayName(value = "Deve retornar 201 created")
  void shouldReturnOKWhenImportAFile() throws Exception {

    ImportacaoCompletaDTO importacaoCompletaDTO = new ImportacaoCompletaDTO(1L, "Importação Concluída");

    when(candidatoService.salvarListaDeCandidatos())
        .thenReturn(importacaoCompletaDTO);

    mockMvc.perform(MockMvcRequestBuilders.post("/candidatos/importar")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());

  }

}
