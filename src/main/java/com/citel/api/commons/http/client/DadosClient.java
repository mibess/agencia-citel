package com.citel.api.commons.http.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.citel.api.commons.exceptions.GenericException;
import com.citel.api.http.dto.input.CandidatoDto;

public class DadosClient {
  private static final String ENDPOINT = "https://citel-bucket.s3.amazonaws.com/data.json";

  public List<CandidatoDto> buscaCandidatos() {
    RestTemplate restTemplate = new RestTemplate();

    try {
      return restTemplate.exchange(
          String.format(ENDPOINT),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<CandidatoDto>>() {
          }).getBody();

    } catch (Exception e) {
      throw new GenericException("Ocorreu um erro ao Importar Candidatos");
    }

  }
}
