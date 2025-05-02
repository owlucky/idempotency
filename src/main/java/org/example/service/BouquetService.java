package org.example.service;
import org.example.models.Bouquet;
import org.example.models.Flower;
import org.example.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.repository.FlowerRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BouquetService {

    @Autowired
    private BouquetRepository bouquetRepository;
    @Autowired
    private FlowerRepository flowerRepository;

    public List<Bouquet> getAllBouquets() {
        return bouquetRepository.findAll();
    }

    public Optional<Bouquet> getBouquetById(Long id) {
        return bouquetRepository.findById(id);
    }

    public Bouquet createBouquet(Bouquet bouquet) {
        Flower flower = flowerRepository.findById(bouquet.getFlower().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Цветок не найден"));
        bouquet.setFlower(flower);
        return bouquetRepository.save(bouquet);
    }

    public Bouquet updateBouquet(Long id, Bouquet updatedBouquet) {
        Bouquet existingBouquet = bouquetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Букет не найден"));

        Flower flower = flowerRepository.findById(updatedBouquet.getFlower().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Цветок не найден"));

        updatedBouquet.setId(existingBouquet.getId());
        updatedBouquet.setFlower(flower);

        return bouquetRepository.save(updatedBouquet);
    }

    public void deleteBouquet(Long id) {
        bouquetRepository.deleteById(id);
    }
}
