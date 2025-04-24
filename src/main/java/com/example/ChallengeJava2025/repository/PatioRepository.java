package com.example.ChallengeJava2025.repository;

import com.example.ChallengeJava2025.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
    List<Patio> findByNomeContaining(String nome);
    
    @Query("SELECT p FROM Patio p WHERE SIZE(p.motos) < p.capacidade")
    List<Patio> findPatiosComVagas();
} 