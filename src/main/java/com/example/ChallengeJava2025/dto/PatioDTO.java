package com.example.ChallengeJava2025.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatioDTO {
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "O endereço é obrigatório")
    @Size(min = 5, max = 255, message = "O endereço deve ter entre 5 e 255 caracteres")
    private String endereco;
    
    @Positive(message = "A capacidade deve ser um número positivo")
    private Integer capacidade;
    
    private Integer ocupacaoAtual;
} 