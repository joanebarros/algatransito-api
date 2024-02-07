package com.algaworks.algatransito.api.model.inpt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AutuacaoInput {

    /*
      Essa anotação indica que o campo não pode ser vazio
     */
    @NotBlank
    private String descricao;

    /* @NotNull
       Essa anotação indica que o campo não pode ser nulo
       @Positive
       Essa anotação indica que o campo não pode ser negativo
     */
    @NotNull
    @Positive
    private BigDecimal valorMulta;
}
