package com.citel.api.infra.http.client;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import com.citel.api.app.exceptions.GenericException;
import com.citel.api.domain.candidato.input.CandidatoImportarInput;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CandidatosJsonAWS implements CandidatoJson {
  private static final String ENDPOINT = "https://citel-bucket.s3.amazonaws.com/candidatos.json";

  @Override
  public List<CandidatoImportarInput> importarCandidatos() {
    try {
      RestTemplate restTemplate = new RestTemplate();

      String jsonResponse = restTemplate.getForObject(ENDPOINT, String.class);

      ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.readValue(jsonResponse, new TypeReference<List<CandidatoImportarInput>>() {
      });

    } catch (Exception e) {
      throw new GenericException(
          String.format("Ocorreu um erro ao Importar Candidatos: %s: %s", e.getMessage(), e.getCause()));
    }
  }
}
