package com.example.ChallengeJava2025.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A placa é obrigatória")
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", message = "Formato de placa inválido")
    private String placa;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(min = 2, max = 50, message = "O modelo deve ter entre 2 e 50 caracteres")
    private String modelo;

    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;

    private LocalDateTime ultimaAtualizacao;

    public enum StatusMoto {
        DISPONIVEL, EM_USO, MANUTENCAO, INATIVA
    }
} 