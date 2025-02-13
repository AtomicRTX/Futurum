package com.kubacki.dawid.AppFuturum.service;

import com.kubacki.dawid.AppFuturum.models.Town;
import com.kubacki.dawid.AppFuturum.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }
    public Town getTownById(int id) {
        return townRepository.findById(id).orElseThrow(() -> new RuntimeException("Town not found"));
    }
}
