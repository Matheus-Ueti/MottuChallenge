package com.example.ChallengeJava2025.controller;

import com.example.ChallengeJava2025.dto.MotoDTO;
import com.example.ChallengeJava2025.model.Moto;
import com.example.ChallengeJava2025.repository.MotoRepository;
import com.example.ChallengeJava2025.repository.PatioRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@RequestMapping("/motos")
public class MotoController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MotoRepository motoRepository;
    
    @Autowired
    private PatioRepository patioRepository;

    @GetMapping
    @Cacheable(value = "motos")
    public List<Moto> index() {
        log.info("Listando todas as motos");
        return motoRepository.findAll();
    }
    
    @GetMapping("/paginado")
    public Page<Moto> listarPaginado(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Listando motos com paginação");
        return motoRepository.findAll(pageable);
    }

    @PostMapping
    @CacheEvict(value = "motos", allEntries = true)
    public ResponseEntity<Moto> create(@RequestBody @Valid MotoDTO motoDTO) {
        log.info("Cadastrando moto com placa: " + motoDTO.getPlaca());
        
        Moto moto = new Moto();
        moto.setPlaca(motoDTO.getPlaca());
        moto.setModelo(motoDTO.getModelo());
        moto.setStatus(motoDTO.getStatus());
        moto.setUltimaAtualizacao(LocalDateTime.now());
        
        if (motoDTO.getPatioId() != null) {
            patioRepository.findById(motoDTO.getPatioId())
                .ifPresent(moto::setPatio);
        }
        
        motoRepository.save(moto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }

    @GetMapping("/{id}")
    public Moto get(@PathVariable Long id) {
        log.info("Buscando moto com id: " + id);
        return getMoto(id);
    }
    
    @GetMapping("/placa/{placa}")
    public Moto getByPlaca(@PathVariable String placa) {
        log.info("Buscando moto com placa: " + placa);
        return motoRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
    }
    
    @GetMapping("/status/{status}")
    public List<Moto> getByStatus(@PathVariable Moto.StatusMoto status) {
        log.info("Buscando motos com status: " + status);
        return motoRepository.findByStatus(status);
    }
    
    @GetMapping("/patio/{patioId}")
    public Page<Moto> getByPatio(@PathVariable Long patioId, @PageableDefault(size = 10) Pageable pageable) {
        log.info("Buscando motos do pátio: " + patioId);
        return motoRepository.findByPatioId(patioId, pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "motos", allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto com id: " + id);
        motoRepository.delete(getMoto(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody @Valid MotoDTO motoDTO) {
        log.info("Atualizando moto com id: " + id);
        
        Moto moto = getMoto(id);
        moto.setPlaca(motoDTO.getPlaca());
        moto.setModelo(motoDTO.getModelo());
        moto.setStatus(motoDTO.getStatus());
        moto.setUltimaAtualizacao(LocalDateTime.now());
        
        if (motoDTO.getPatioId() != null) {
            patioRepository.findById(motoDTO.getPatioId())
                .ifPresent(moto::setPatio);
        } else {
            moto.setPatio(null);
        }
        
        return motoRepository.save(moto);
    }
    
    private Moto getMoto(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
    }
} 