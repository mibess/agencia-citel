package com.citel.api.commons.validations;

public class CPFValidation {

  private CPFValidation() {
  }

  // Fatores usados no cálculo do dígito verificador do CPF.
  private static final int[] CPF_FACTORS = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

  public static boolean isValidCPF(String cpf) {
    cpf = replaceString(cpf);

    // Verifica se o CPF tem o comprimento esperado.
    if (!hasValidLength(cpf, 11)) {
      return false;
    }

    // Calcula os dígitos verificadores e compara com os dígitos fornecidos no CPF.
    Integer digit1 = calculateDigit(cpf.substring(0, 9), CPF_FACTORS);
    Integer digit2 = calculateDigit(cpf.substring(0, 9) + digit1, CPF_FACTORS);

    return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
  }

  public static String replaceString(String cpf) {
    return cpf.replace(".", "").replace("-", "");
  }

  public static boolean hasValidLength(String cpf, int expectedLength) {
    return cpf.length() == expectedLength;
  }

  private static int calculateDigit(String number, int[] weights) {
    int sum = 0;
    for (int i = number.length() - 1; i >= 0; i--) {
      int digit = Integer.parseInt(number.substring(i, i + 1));
      sum += digit * weights[weights.length - number.length() + i];
    }
    int remainder = sum % 11;
    int result = 11 - remainder;
    return result > 9 ? 0 : result;
  }

}
