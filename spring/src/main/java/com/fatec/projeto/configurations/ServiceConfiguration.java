package com.fatec.projeto.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fatec.projeto.repositories.PessoaRepository;
import com.fatec.projeto.services.PessoaService;

@Configuration
public class ServiceConfiguration {
    
    @Bean
    public PessoaService pessoaService(PessoaRepository pessoaRepository) {
        return new PessoaService(pessoaRepository);
    }

}
