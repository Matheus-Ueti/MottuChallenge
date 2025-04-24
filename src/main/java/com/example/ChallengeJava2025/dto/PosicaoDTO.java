package com.example.ChallengeJava2025.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosicaoDTO {
    private Long id;
    
    @NotNull(message = "O ID da moto é obrigatório")
    private Long motoId;
    
    @NotNull(message = "A latitude é obrigatória")
    private Double latitude;
    
    @NotNull(message = "A longitude é obrigatória")
    private Double longitude;
    
    private LocalDateTime timestamp;
} 