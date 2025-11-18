package com.fatec.projeto.dtos.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record PessoaCriarRequestDto(
    @NotBlank String nome,
    @NotNull @PastOrPresent Date dataNascimento
) {
}
