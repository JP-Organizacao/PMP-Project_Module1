package com.fatec.projeto.dtos.response;

import java.util.Date;

public record PessoaResponseDto(
    String id,
    String nome,
    Date dataNascimento
) {
}
