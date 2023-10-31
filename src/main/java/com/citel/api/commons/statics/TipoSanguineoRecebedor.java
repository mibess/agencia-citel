package com.citel.api.commons.statics;

import java.util.List;

public class TipoSanguineoRecebedor {
  private TipoSanguineoRecebedor() {
  }

  public static List<String> aceita(String tipoSanguineo) {
    List<String> tiposAceitaveis;
    switch (tipoSanguineo) {
      case "A+":
        tiposAceitaveis = List.of("A+", "A-", "O+", "O-");
        break;
      case "A-":
        tiposAceitaveis = List.of("A-", "O-");
        break;
      case "B+":
        tiposAceitaveis = List.of("B+", "B-", "O+", "O-");
        break;
      case "B-":
        tiposAceitaveis = List.of("B-", "O-");
        break;
      case "AB+":
        tiposAceitaveis = List.of("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-");
        break;
      case "AB-":
        tiposAceitaveis = List.of("A-", "B-", "O-", "AB-");
        break;
      case "O+":
        tiposAceitaveis = List.of("O+", "O-");
        break;
      case "O-":
        tiposAceitaveis = List.of("O-");
        break;

      default:
        tiposAceitaveis = List.of();
        break;
    }
    return tiposAceitaveis;
  }
}
