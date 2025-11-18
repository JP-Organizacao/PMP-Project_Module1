package com.fatec.projeto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fatec.projeto.entities.Pessoa;
import com.fatec.projeto.errors.NotFoundException;
import com.fatec.projeto.repositories.adapters.PessoaRepositoryAdapter;
import com.fatec.projeto.repositories.orms.PessoaOrm;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaRepositoryMongoDb repository;

    public PessoaRepositoryImpl(PessoaRepositoryMongoDb repository) {
        this.repository = repository;
    }

    @Override
    public List<Pessoa> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PessoaOrm> pessoaOrmsPage = repository.findAllAtivos(pageable);
        List<PessoaOrm> pessoaOrms = pessoaOrmsPage.getContent();
        
        return PessoaRepositoryAdapter.cast(pessoaOrms);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        PessoaOrm pessoaOrm = PessoaRepositoryAdapter.cast(pessoa);
        return PessoaRepositoryAdapter.cast(repository.save(pessoaOrm));
    }

    @Override
    public Pessoa findById(String id) {
        Optional<PessoaOrm> pessoaOrmOptional = repository.findByIdAtivo(id);
        if (pessoaOrmOptional.isEmpty()) {
            throw new NotFoundException("Pessoa não encontrada");
        }
        return PessoaRepositoryAdapter.cast(pessoaOrmOptional.get());
    }
}
