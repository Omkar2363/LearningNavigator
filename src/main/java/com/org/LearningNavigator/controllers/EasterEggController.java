package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.services.EasterEggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EasterEggController {

    @Autowired
    private EasterEggService easterEggService;

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getRandomFactAboutNumber(@PathVariable Integer number){
        return ResponseEntity.ok().body(easterEggService.getRandomFactAboutNumber(number));
    }
}