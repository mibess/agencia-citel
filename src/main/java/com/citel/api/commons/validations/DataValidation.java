package com.citel.api.commons.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataValidation {

  private DataValidation() {
  }

  public static boolean isDataValid(String data) {
    return isDataValid(data, null);
  }

  public static boolean isDataValid(String data, String pattern) {
    SimpleDateFormat sdf;
    if (pattern == null || pattern.isEmpty()) {
      sdf = new SimpleDateFormat("dd/MM/yyyy");
    } else {
      sdf = new SimpleDateFormat(pattern);
    }

    sdf.setLenient(false); // Desativa o modo leniente para rejeitar datas inválidas

    try {
      sdf.parse(data);
      // Se a data foi convertida sem lançar exceção, ela é válida
      return true;
    } catch (ParseException e) {
      // A data não pôde ser convertida, portanto, é inválida
      return false;
    }
  }

}
