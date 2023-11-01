package com.citel.api.http.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.citel.api.http.dto.CandidatoDTO;
import com.citel.api.http.dto.CandidatoResumidoDTO;
import com.citel.api.http.input.CandidatoImportarInput;
import com.citel.api.http.input.CandidatoInput;
import com.citel.api.models.candidato.Candidato;
import com.citel.api.models.candidato.Endereco;

@Component
public class CandidatoConverter {

  public Candidato fromCandidatoInputToCandidato(CandidatoInput candidatoInput) {
    Candidato candidato = new Candidato();

    LocalDate nascimentoTratado = LocalDate.parse(candidatoInput.getDataNasc(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    Endereco enderecoEmb = new Endereco(candidatoInput.getCep(), candidatoInput.getEndereco(),
        candidatoInput.getNumero(), candidatoInput.getBairro(), candidatoInput.getCidade(), candidatoInput.getEstado());

    candidato.setNome(candidatoInput.getNome());
    candidato.setCpf(candidatoInput.getCpf());
    candidato.setRg(candidatoInput.getRg());
    candidato.setDataNascimento(nascimentoTratado);
    candidato.setSexo(candidatoInput.getSexo());
    candidato.setMae(candidatoInput.getMae());
    candidato.setPai(candidatoInput.getPai());
    candidato.setEmail(candidatoInput.getEmail());
    candidato.setEndereco(enderecoEmb);
    candidato.setTelefoneFixo(candidatoInput.getTelefoneFixo());
    candidato.setCelular(candidatoInput.getCelular());
    candidato.setAltura(candidatoInput.getAltura());
    candidato.setPeso(candidatoInput.getPeso());
    candidato.setTipoSanguineo(candidatoInput.getTipoSanguineo());

    return candidato;
  }

  public CandidatoDTO toCandidatoDTO(Candidato candidato) {
    return new CandidatoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getRg(),
        candidato.getDataNascimento().toString(),
        candidato.getSexo(),
        candidato.getMae(),
        candidato.getPai(),
        candidato.getEmail(),
        candidato.getEndereco().getCep(),
        candidato.getEndereco().getEndereco(),
        candidato.getEndereco().getNumero(),
        candidato.getEndereco().getBairro(),
        candidato.getEndereco().getCidade(),
        candidato.getEndereco().getEstado(),
        candidato.getTelefoneFixo(),
        candidato.getCelular(),
        candidato.getAltura(),
        candidato.getPeso(),
        candidato.getTipoSanguineo());
  }

  public CandidatoResumidoDTO toCandidatoResumidoDTO(Candidato candidato) {
    return new CandidatoResumidoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getEmail(),
        candidato.getTipoSanguineo());
  }

  public Candidato fromCandidatoImportarInputToCandidato(CandidatoImportarInput candidatoImportarInput) {
    Candidato candidato = new Candidato();

    LocalDate nascimentoTratado = LocalDate.parse(candidatoImportarInput.getDataNasc(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    Endereco enderecoEmb = new Endereco(candidatoImportarInput.getCep(), candidatoImportarInput.getEndereco(),
        candidatoImportarInput.getNumero(), candidatoImportarInput.getBairro(), candidatoImportarInput.getCidade(),
        candidatoImportarInput.getEstado());

    candidato.setNome(candidatoImportarInput.getNome());
    candidato.setCpf(candidatoImportarInput.getCpf());
    candidato.setRg(candidatoImportarInput.getRg());
    candidato.setDataNascimento(nascimentoTratado);
    candidato.setSexo(candidatoImportarInput.getSexo());
    candidato.setMae(candidatoImportarInput.getMae());
    candidato.setPai(candidatoImportarInput.getPai());
    candidato.setEmail(candidatoImportarInput.getEmail());
    candidato.setEndereco(enderecoEmb);
    candidato.setTelefoneFixo(candidatoImportarInput.getTelefoneFixo());
    candidato.setCelular(candidatoImportarInput.getCelular());
    candidato.setAltura(candidatoImportarInput.getAltura());
    candidato.setPeso(candidatoImportarInput.getPeso());
    candidato.setTipoSanguineo(candidatoImportarInput.getTipoSanguineo());

    return candidato;
  }
}
