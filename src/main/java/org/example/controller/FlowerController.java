package org.example.controller;

import org.example.models.Flower;
import org.example.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @GetMapping("/")
    public String showFlowers(Model model) {
        List<Flower> flowers = flowerService.getAllFlowers();
        model.addAttribute("flowers", flowers);
        model.addAttribute("newFlower", new Flower());
        return "flowers";
    }

    @PostMapping("/flowers")
    public String addFlower(@ModelAttribute Flower flower) {
        flowerService.createFlower(flower);
        return "redirect:/";
    }

    @GetMapping("/flowers/{id}/edit")
    public String editFlower(@PathVariable("id") Long id, Model model) {
       Flower flower = flowerService.getFlowerById(id);
        model.addAttribute("flower", flower);
        return "editFlower";
    }

    @PostMapping("/flowers/{id}/update")
    public String updateFlower(@PathVariable("id") Long id, @ModelAttribute Flower updatedFlower) {
        flowerService.updateFlower(id, updatedFlower);
        return "redirect:/";
    }

    @GetMapping("/flowers/{id}/delete")
    public String deleteFlower(@PathVariable("id") Long id) {
        flowerService.deleteFlower(id);
        return "redirect:/";
    }
}