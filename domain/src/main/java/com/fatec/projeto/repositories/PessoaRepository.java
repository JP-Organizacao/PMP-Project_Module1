package com.fatec.projeto.repositories;

import java.util.List;

import com.fatec.projeto.entities.Pessoa;

public interface PessoaRepository {
    Pessoa save(Pessoa pessoa);
    Pessoa findById(String id);
    List<Pessoa> findAll(int size, int page);
}
