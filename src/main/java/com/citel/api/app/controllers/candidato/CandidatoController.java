package com.citel.api.app.controllers.candidato;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citel.api.domain.candidato.dto.CandidatoResumidoDTO;
import com.citel.api.domain.candidato.dto.ImportacaoCompletaDTO;
import com.citel.api.domain.candidato.input.CandidatoImportarInput;
import com.citel.api.domain.candidato.input.CandidatoInput;
import com.citel.api.domain.candidato.services.CandidatoService;
import com.citel.api.infra.http.client.CandidatoJson;
import com.citel.api.infra.http.client.CandidatosJsonAWS;
import com.citel.api.infra.http.client.CandidatosJsonLocal;
import com.citel.api.core.importacao.ImportacaoConfig;
import com.citel.api.core.importacao.ImportacaoConfig.TipoImportacao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

  private CandidatoService candidatoService;

  public CandidatoController(
      CandidatoService candidatoService) {

    this.candidatoService = candidatoService;
  }

  @GetMapping("/listar-todos")
  public List<CandidatoResumidoDTO> listarTodos() {

    return candidatoService.listarTodosCandidatos();

  }

  @PostMapping("/salvar")
  public ResponseEntity<CandidatoResumidoDTO> salvar(@RequestBody @Valid CandidatoInput candidatoInput) {

    return ResponseEntity.status(HttpStatus.CREATED).body(candidatoService.criarCandidato(candidatoInput));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping("/importar")
  public ImportacaoCompletaDTO importarDados() {

    return candidatoService.salvarListaDeCandidatos();
  }

}
