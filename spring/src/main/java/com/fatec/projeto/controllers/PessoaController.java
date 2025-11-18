package com.fatec.projeto.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.projeto.adapters.PessoaControllerAdapter;
import com.fatec.projeto.dtos.request.PessoaAtualizarRequestDto;
import com.fatec.projeto.dtos.request.PessoaCriarRequestDto;
import com.fatec.projeto.dtos.response.PessoaResponseDto;
import com.fatec.projeto.entities.Pessoa;
import com.fatec.projeto.services.PessoaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("v1/pessoas")
@Validated
public class PessoaController {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDto>> listarPessoas(
        @RequestParam(value = "size", defaultValue = "10") @Min(message = "'size' deve ser maior ou igual a 1", value = 1) int size,
        @RequestParam(value = "page", defaultValue = "0") @Min(message = "'page' deve ser maior ou igual a 0", value = 0) int page
    ) {
        LOG.info("Listando pessoas - size: {}, page: {}", size, page);
        List<Pessoa> pessoas = pessoaService.listar(size, page);
        return ResponseEntity.ok(PessoaControllerAdapter.cast(pessoas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> obterPessoa(@PathVariable("id") String id) {
        LOG.info("Obtendo pessoa - id: {}", id);
        Pessoa pessoa = pessoaService.getById(id);
        return ResponseEntity.ok(PessoaControllerAdapter.cast(pessoa));
    }
    
    @PostMapping
    public ResponseEntity<PessoaResponseDto> criarPessoa(@RequestBody @Valid PessoaCriarRequestDto requestDto) {
        LOG.info("Criando pessoa - nome: {}", requestDto.nome());
        Pessoa pessoaCriada = pessoaService.criar(PessoaControllerAdapter.cast(requestDto));
        return ResponseEntity.status(201).body(
            PessoaControllerAdapter.cast(pessoaCriada)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> atualizarPessoa(
        @PathVariable("id") String id,
        @RequestBody PessoaAtualizarRequestDto requestDto
    ) {
        LOG.info("Atualizando pessoa - id: {}", id);
        Pessoa pessoaAtualizada = pessoaService.atualizar(
            id,
            PessoaControllerAdapter.cast(requestDto)
        );
        return ResponseEntity.ok(
            PessoaControllerAdapter.cast(pessoaAtualizada)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable("id") String id) {
        LOG.info("Deletando pessoa - id: {}", id);
        pessoaService.deletar(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso");
    }
}
