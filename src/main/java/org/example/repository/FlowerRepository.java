package org.example.repository;

import org.example.models.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    Optional<Flower> findByIdempotencyKey(String idempotencyKey);
}
