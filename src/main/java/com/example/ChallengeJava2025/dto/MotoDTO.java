package com.example.ChallengeJava2025.dto;

import com.example.ChallengeJava2025.model.Moto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotoDTO {
    private Long id;
    
    @NotBlank(message = "A placa é obrigatória")
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", message = "Formato de placa inválido")
    private String placa;
    
    @NotBlank(message = "O modelo é obrigatório")
    @Size(min = 2, max = 50, message = "O modelo deve ter entre 2 e 50 caracteres")
    private String modelo;
    
    private Moto.StatusMoto status;
    
    private Long patioId;
} 