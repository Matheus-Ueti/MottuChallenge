package com.example.ChallengeJava2025.repository;

import com.example.ChallengeJava2025.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    Optional<Moto> findByPlaca(String placa);
    List<Moto> findByStatus(Moto.StatusMoto status);
    Page<Moto> findByPatioId(Long patioId, Pageable pageable);
    List<Moto> findByModelo(String modelo);
} 