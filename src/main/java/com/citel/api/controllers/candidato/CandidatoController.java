package com.citel.api.controllers.candidato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citel.api.commons.http.client.DadosClient;
import com.citel.api.http.dto.CandidatoDTO;
import com.citel.api.http.dto.CandidatoPorEstadoDTO;
import com.citel.api.http.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.http.dto.ImportacaoCompletaDTO;
import com.citel.api.services.candidato.CandidatoService;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

  @Autowired
  private CandidatoService candidatoService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/importar")
  public ImportacaoCompletaDTO importarDados() {

    List<CandidatoDTO> listaCandidatoDtos = new DadosClient().buscaCandidatos();

    return candidatoService.salvarLista(listaCandidatoDtos);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/por-estado")
  public List<CandidatoPorEstadoDTO> listaCandidatosPorEstado() {

    return candidatoService.candidatosPorEstado();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/imc-por-faixa-idade")
  public List<ImcPorFaixaDeIdadeDeDezAnosDTO> imcPorFaixaIdade() {

    return candidatoService.candidatosPorImcFaixaDeIdadeDeDezAnos();
  }
}
