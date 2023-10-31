package com.citel.api.commons.http.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.citel.api.commons.exceptions.GenericException;
import com.citel.api.http.dto.CandidatoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DadosClient {
  private static final String ENDPOINT = "https://citel-bucket.s3.amazonaws.com/candidatos.json";

  public List<CandidatoDTO> buscaCandidatos() {
    try {
      RestTemplate restTemplate = new RestTemplate();

      String jsonResponse = restTemplate.getForObject(ENDPOINT, String.class);

      ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.readValue(jsonResponse, new TypeReference<List<CandidatoDTO>>() {
      });

    } catch (Exception e) {
      throw new GenericException(
          String.format("Ocorreu um erro ao Importar Candidatos: %s: %s", e.getMessage(), e.getCause()));
    }

  }

  public List<CandidatoDTO> buscaCandidatosLocal() throws IOException {
    String file = lerArquivo("files/candidatos.json"); // leitura de arquivo local

    // Inicializa o ObjectMapper do Jackson
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(file, new TypeReference<List<CandidatoDTO>>() {
    });
  }

  public static String lerArquivo(String nomeArquivo) throws IOException {
    Resource resource = new ClassPathResource(nomeArquivo);
    return new String(resource.getInputStream().readAllBytes());
  }
}
