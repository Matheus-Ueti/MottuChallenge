package com.example.ChallengeJava2025.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "moto_id")
    private Moto moto;

    private Boolean ligado;
    
    private Integer nivelBateria;
    
    private Integer nivelCombustivel;
    
    private LocalDateTime ultimaAtualizacao;
    
    @PreUpdate
    public void preUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
} 