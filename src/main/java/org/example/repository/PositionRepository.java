package org.example.repository;

import org.example.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;


public interface PositionRepository extends JpaRepository<Position, UUID> {
    Optional<Position> findByCode(String code);
}

