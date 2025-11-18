package com.fatec.projeto.entities;

import java.util.Date;

public record Pessoa(
    String id,
    String nome,
    Date dataNascimento,
    Boolean ativo
) {
}
