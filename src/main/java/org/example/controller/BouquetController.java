package org.example.controller;

import org.example.models.Bouquet;
import org.example.models.Flower;
import org.example.service.BouquetService;
import org.example.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BouquetController {

    @Autowired
    private BouquetService bouquetService;

    @Autowired
    private FlowerService flowerService;

    @GetMapping("/bouquets")
    public String showBouquets(Model model) {
        List<Bouquet> bouquets = bouquetService.getAllBouquets();
        List<Flower> flowers = flowerService.getAllFlowers();
        model.addAttribute("bouquets", bouquets);
        model.addAttribute("flowers", flowers);
        model.addAttribute("bouquet", new Bouquet());
        return "bouquets";
    }

    @PostMapping("/bouquets")
    public String createBouquet(@ModelAttribute Bouquet bouquet) {
        bouquetService.createBouquet(bouquet);
        return "redirect:/bouquets";
    }
}