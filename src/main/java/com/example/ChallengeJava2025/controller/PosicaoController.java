package com.example.ChallengeJava2025.controller;

import com.example.ChallengeJava2025.dto.PosicaoDTO;
import com.example.ChallengeJava2025.model.Moto;
import com.example.ChallengeJava2025.model.Posicao;
import com.example.ChallengeJava2025.repository.MotoRepository;
import com.example.ChallengeJava2025.repository.PosicaoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posicoes")
public class PosicaoController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PosicaoRepository posicaoRepository;
    
    @Autowired
    private MotoRepository motoRepository;

    @GetMapping("/moto/{motoId}")
    public List<Posicao> getPosicoesByMoto(@PathVariable Long motoId) {
        log.info("Buscando posições da moto: " + motoId);
        return posicaoRepository.findByMotoId(motoId);
    }
    
    @GetMapping("/moto/{motoId}/paginado")
    public Page<Posicao> getPosicoesByMotoPaginado(
            @PathVariable Long motoId, 
            @PageableDefault(size = 10, sort = "timestamp") Pageable pageable) {
        log.info("Buscando posições da moto com paginação: " + motoId);
        return posicaoRepository.findByMotoId(motoId, pageable);
    }

    @PostMapping
    public ResponseEntity<Posicao> registrarPosicao(@RequestBody @Valid PosicaoDTO posicaoDTO) {
        log.info("Registrando posição para moto: " + posicaoDTO.getMotoId());
        
        Moto moto = motoRepository.findById(posicaoDTO.getMotoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
        
        Posicao posicao = new Posicao();
        posicao.setMoto(moto);
        posicao.setLatitude(posicaoDTO.getLatitude());
        posicao.setLongitude(posicaoDTO.getLongitude());
        
        // Atualiza a última atualização da moto
        moto.setUltimaAtualizacao(LocalDateTime.now());
        motoRepository.save(moto);
        
        posicaoRepository.save(posicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(posicao);
    }
    
    @GetMapping("/periodo")
    public List<Posicao> getPosicoesPorPeriodo(
            @RequestParam LocalDateTime inicio, 
            @RequestParam LocalDateTime fim) {
        log.info("Buscando posições no período de " + inicio + " até " + fim);
        return posicaoRepository.findByTimestampBetween(inicio, fim);
    }
} 