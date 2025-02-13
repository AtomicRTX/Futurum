package com.kubacki.dawid.AppFuturum.controller;

import com.kubacki.dawid.AppFuturum.models.Town;
import com.kubacki.dawid.AppFuturum.repositories.TownRepository;
import com.kubacki.dawid.AppFuturum.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {

    @Autowired
    private TownService townService;

    @GetMapping("/all")
    public ResponseEntity<List<Town>> getAllTowns() {
        List<Town> towns = townService.getAllTowns();
        return ResponseEntity.ok(towns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Town> getTownById(@PathVariable int id) {
        Town town = townService.getTownById(id);
        return ResponseEntity.ok(town);
    }
}
