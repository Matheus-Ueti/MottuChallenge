package com.example.ChallengeJava2025.repository;

import com.example.ChallengeJava2025.model.Posicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {
    List<Posicao> findByMotoId(Long motoId);
    Page<Posicao> findByMotoId(Long motoId, Pageable pageable);
    List<Posicao> findByTimestampBetween(LocalDateTime inicio, LocalDateTime fim);
} 