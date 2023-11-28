package com.citel.api.core.importacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportacaoConfig {

  @Value("${citel.config.tipo-importacao}")
  private String tipoImportacao;

  public TipoImportacao getTipoImportacao() {
    return TipoImportacao.valueOf(tipoImportacao.toUpperCase());
  }

  public enum TipoImportacao {
    LOCAL, AWS_S3
  }
}
