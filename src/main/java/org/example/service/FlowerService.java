package org.example.service;

import org.example.models.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.repository.FlowerRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public Flower getFlowerById(Long id) {
        return flowerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Цветок не найден"));
    }

    public Flower createFlower(Flower flower) {
        return flowerRepository.save(flower);
    }
    public Flower createFlowerIfIdempotent(Flower flower, String idempotencyKey) {
        Optional<Flower> existing = flowerRepository.findByIdempotencyKey(idempotencyKey);
        if (existing.isPresent()) {
            throw new RuntimeException();
        }

        flower.setIdempotencyKey(idempotencyKey);
        return flowerRepository.save(flower);
    }

    public Flower updateFlower(Long id, Flower updatedFlower) {
        Optional<Flower> existingFlower = flowerRepository.findById(id);

        updatedFlower.setId(id);
        return flowerRepository.save(updatedFlower);
    }

    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }
}
