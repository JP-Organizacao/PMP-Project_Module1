package com.fatec.projeto.adapters;

import java.util.List;
import java.util.UUID;

import com.fatec.projeto.dtos.request.PessoaAtualizarRequestDto;
import com.fatec.projeto.dtos.request.PessoaCriarRequestDto;
import com.fatec.projeto.dtos.response.PessoaResponseDto;
import com.fatec.projeto.entities.Pessoa;

public class PessoaControllerAdapter {
    public static Pessoa cast(PessoaCriarRequestDto requestDto) {
        return new Pessoa(
            UUID.randomUUID().toString(),
            requestDto.nome(),
            requestDto.dataNascimento(),
            true
        );
    }

    public static Pessoa cast(PessoaAtualizarRequestDto requestDto) {
        return new Pessoa(
            null,
            requestDto.nome(),
            requestDto.dataNascimento(),
            null
        );
    }

    public static PessoaResponseDto cast(Pessoa pessoa) {
        return new PessoaResponseDto(
            pessoa.id(),
            pessoa.nome(),
            pessoa.dataNascimento()
        );
    }

    public static List<PessoaResponseDto> cast(List<Pessoa> pessoas) {
        return pessoas.stream()
            .map(PessoaControllerAdapter::cast)
            .toList();
    }
}
