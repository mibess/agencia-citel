package com.citel.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citel.api.repository.CandidatoRepository;
import com.citel.api.services.candidato.CandidatoService;
import com.citel.api.utils.CandidatoUtilsTest;

@ExtendWith(MockitoExtension.class)
class CandidatoServiceTest {

  @InjectMocks
  private CandidatoService candidatoService;

  @Mock
  private CandidatoRepository candidatoRepository;

  @Test
  void shouldReturnCandidatoDTOWhenSaveAListCandidato() {
    var listaCandidatos = CandidatoUtilsTest.getListaCandidato();

    Object result = candidatoRepository.saveAll(listaCandidatos);

    assertNotNull(result);
  }
}
