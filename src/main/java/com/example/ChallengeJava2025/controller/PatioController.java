package com.example.ChallengeJava2025.controller;

import com.example.ChallengeJava2025.dto.PatioDTO;
import com.example.ChallengeJava2025.model.Patio;
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

import java.util.List;

@RestController
@RequestMapping("/patios")
public class PatioController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PatioRepository repository;

    @GetMapping
    @Cacheable(value = "patios")
    public List<Patio> index() {
        log.info("Listando todos os pátios");
        return repository.findAll();
    }
    
    @GetMapping("/com-vagas")
    public List<Patio> patiosComVagas() {
        log.info("Listando pátios com vagas disponíveis");
        return repository.findPatiosComVagas();
    }

    @PostMapping
    @CacheEvict(value = "patios", allEntries = true)
    public ResponseEntity<Patio> create(@RequestBody @Valid PatioDTO patioDTO) {
        log.info("Cadastrando pátio: " + patioDTO.getNome());
        
        Patio patio = new Patio();
        patio.setNome(patioDTO.getNome());
        patio.setEndereco(patioDTO.getEndereco());
        patio.setCapacidade(patioDTO.getCapacidade());
        
        repository.save(patio);
        return ResponseEntity.status(HttpStatus.CREATED).body(patio);
    }

    @GetMapping("/{id}")
    public Patio get(@PathVariable Long id) {
        log.info("Buscando pátio com id: " + id);
        return getPatio(id);
    }
    
    @GetMapping("/busca")
    public List<Patio> buscarPorNome(@RequestParam String nome) {
        log.info("Buscando pátios com nome contendo: " + nome);
        return repository.findByNomeContaining(nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "patios", allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pátio com id: " + id);
        repository.delete(getPatio(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "patios", allEntries = true)
    public Patio update(@PathVariable Long id, @RequestBody @Valid PatioDTO patioDTO) {
        log.info("Atualizando pátio com id: " + id);
        
        Patio patio = getPatio(id);
        patio.setNome(patioDTO.getNome());
        patio.setEndereco(patioDTO.getEndereco());
        patio.setCapacidade(patioDTO.getCapacidade());
        
        return repository.save(patio);
    }
    
    @GetMapping("/paginado")
    public Page<Patio> listarPaginado(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Listando pátios com paginação");
        return repository.findAll(pageable);
    }
    
    private Patio getPatio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado"));
    }
} 