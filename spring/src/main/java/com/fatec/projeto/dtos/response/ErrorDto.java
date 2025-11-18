package com.fatec.projeto.dtos.response;

import java.time.LocalDateTime;

public record ErrorDto(
    String mensagem,
    String erro,
    int status,
    String path,
    LocalDateTime data
) {
}
