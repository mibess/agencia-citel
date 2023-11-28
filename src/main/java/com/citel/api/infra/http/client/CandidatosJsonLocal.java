package com.citel.api.infra.http.client;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.citel.api.app.exceptions.GenericException;
import com.citel.api.domain.candidato.input.CandidatoImportarInput;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CandidatosJsonLocal implements CandidatoJson {

  @Override
  public List<CandidatoImportarInput> importarCandidatos() {
    try {
      String file = lerArquivo("files/candidatos.json");

      ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.readValue(file, new TypeReference<List<CandidatoImportarInput>>() {
      });
    } catch (Exception e) {
      throw new GenericException(
          String.format("Ocorreu um erro ao Importar Candidatos: %s: %s", e.getMessage(), e.getCause()));
    }

  }

  public static String lerArquivo(String nomeArquivo) throws IOException {
    Resource resource = new ClassPathResource(nomeArquivo);
    return new String(resource.getInputStream().readAllBytes());
  }

}
