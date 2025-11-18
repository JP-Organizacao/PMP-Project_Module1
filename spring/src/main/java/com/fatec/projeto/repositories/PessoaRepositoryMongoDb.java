package com.fatec.projeto.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.projeto.repositories.orms.PessoaOrm;

@Repository
public interface PessoaRepositoryMongoDb extends MongoRepository<PessoaOrm, String> {
    @Query("{ ativo: true }")
    Page<PessoaOrm> findAllAtivos(Pageable pageable);

    @Query("{ _id: ?0, ativo: true }")
    Optional<PessoaOrm> findByIdAtivo(String id);
}