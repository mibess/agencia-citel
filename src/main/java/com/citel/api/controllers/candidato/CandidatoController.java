package com.citel.api.controllers.candidato;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citel.api.commons.http.client.DadosClient;
import com.citel.api.http.dto.input.CandidatoDto;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/importar")
  public List<CandidatoDto> importarDados() {

    return new DadosClient().buscaCandidatos();

  }

}
