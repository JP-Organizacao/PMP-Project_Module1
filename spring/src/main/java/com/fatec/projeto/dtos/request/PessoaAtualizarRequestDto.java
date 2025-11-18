package com.fatec.projeto.dtos.request;

import java.util.Date;

public record PessoaAtualizarRequestDto(
    String nome,
    Date dataNascimento
) {
}
