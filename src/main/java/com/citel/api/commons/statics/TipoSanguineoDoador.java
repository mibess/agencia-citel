package com.citel.api.commons.statics;

import java.util.List;

public class TipoSanguineoDoador {
  private TipoSanguineoDoador() {
  }

  public static List<String> aceita(String tipoSanguineo) {
    List<String> tiposAceitaveis;
    switch (tipoSanguineo) {
      case "A+":
        tiposAceitaveis = List.of("AB+", "A+");
        break;
      case "A-":
        tiposAceitaveis = List.of("A+", "A-", "AB+", "AB-");
        break;
      case "B+":
        tiposAceitaveis = List.of("B+", "AB+");
        break;
      case "B-":
        tiposAceitaveis = List.of("B+", "B-", "AB+", "AB-");
        break;
      case "AB+":
        tiposAceitaveis = List.of("AB+");
        break;
      case "AB-":
        tiposAceitaveis = List.of("AB+", "AB-");
        break;
      case "O+":
        tiposAceitaveis = List.of("A+", "B+", "O+", "AB+");
        break;
      case "O-":
        tiposAceitaveis = List.of("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-");
        break;

      default:
        tiposAceitaveis = List.of();
        break;
    }
    return tiposAceitaveis;
  }
}
