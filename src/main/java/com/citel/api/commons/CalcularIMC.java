package com.citel.api.commons;

import org.springframework.stereotype.Component;

@Component
public class CalcularIMC {
  public double calculo(Long peso, Double altura) {
    return peso / (altura * altura);
  }
}
