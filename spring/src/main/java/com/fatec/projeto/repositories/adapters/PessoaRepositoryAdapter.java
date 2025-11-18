package com.fatec.projeto.repositories.adapters;

import java.util.List;

import com.fatec.projeto.entities.Pessoa;
import com.fatec.projeto.repositories.orms.PessoaOrm;

public class PessoaRepositoryAdapter {
    public static PessoaOrm cast(Pessoa pessoa) {
        return new PessoaOrm(
            pessoa.id(),
            pessoa.nome(),
            pessoa.dataNascimento(),
            pessoa.ativo()
        );
    }

    public static Pessoa cast(PessoaOrm pessoaOrm) {
        return new Pessoa(
            pessoaOrm.id(),
            pessoaOrm.nome(),
            pessoaOrm.dataNascimento(),
            pessoaOrm.ativo()
        );
    }

    public static List<Pessoa> cast(List<PessoaOrm> pessoaOrms) {
        return pessoaOrms.stream()
            .map(PessoaRepositoryAdapter::cast)
            .toList();
    }
}
