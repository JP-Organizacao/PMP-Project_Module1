package com.fatec.projeto.repositories.orms;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pessoas")
public record PessoaOrm(
    @Id
    String id,
    @Indexed
    String nome,
    Date dataNascimento,
    boolean ativo
) {
}
