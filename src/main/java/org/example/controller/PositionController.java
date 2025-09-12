package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Position;
import org.example.repository.PositionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionRepository repository;

    @GetMapping
    public List<Position> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Position create(@RequestBody Position position) {
        return repository.save(position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable UUID id, @RequestBody Position updated) {
        return repository.findById(id)
                .map(pos -> {
                    pos.setName(updated.getName());
                    pos.setCode(updated.getCode());
                    return ResponseEntity.ok(repository.save(pos));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return repository.findById(id)
                .map(pos -> {
                    repository.delete(pos);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
