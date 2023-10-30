package com.citel.api.http.dto.input;

import java.util.Date;

public record CandidatoDto(
    String nome,
    String cpf,
    String rg,
    Date dataNascimento,
    String sexo,
    String mae,
    String pai,
    String email,
    String cep,
    String endereco,
    Long numero,
    String bairro,
    String cidade,
    String estado,
    String telefoneFixo,
    String celular,
    Long altura,
    Long peso,
    String tipoSanguineo) {

}
