package com.fatec.projeto.services;

import java.util.List;

import com.fatec.projeto.entities.Pessoa;
import com.fatec.projeto.repositories.PessoaRepository;

public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listar(int size, int page) {
        return pessoaRepository.findAll(size, page);
    }

    public Pessoa getById(String id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(String id, Pessoa pessoa) {
        Pessoa pessoaExistente = pessoaRepository.findById(id);
        return pessoaRepository.save(
            new Pessoa(
                pessoaExistente.id(),
                pessoa.nome() != null && !pessoa.nome().isBlank() ? pessoa.nome() : pessoaExistente.nome(),
                pessoa.dataNascimento() != null ? pessoa.dataNascimento() : pessoaExistente.dataNascimento(),
                pessoaExistente.ativo()
            )
        );
    }

    public void deletar(String id) {
        Pessoa pessoaExistente = pessoaRepository.findById(id);
        pessoaRepository.save(
            new Pessoa(
                pessoaExistente.id(),
                pessoaExistente.nome(),
                pessoaExistente.dataNascimento(),
                false
            )
        );
        
    }
}
