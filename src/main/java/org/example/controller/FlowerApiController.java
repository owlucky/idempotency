package org.example.controller;

import org.example.models.Flower;
import org.example.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flowers")
public class FlowerApiController {

    @Autowired
    private FlowerService flowerService;

    @PostMapping
    public ResponseEntity<Flower> createFlower(
            @RequestBody Flower flower,
            @RequestParam("idempotencyKey") String idempotencyKey) {

        try {
            Flower created = flowerService.createFlowerIfIdempotent(flower, idempotencyKey);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

