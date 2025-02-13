package com.kubacki.dawid.AppFuturum.service;

import com.kubacki.dawid.AppFuturum.models.Keyword;
import com.kubacki.dawid.AppFuturum.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    public List<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    public Keyword getKeywordById(int id) {
        return keywordRepository.findById(id).orElseThrow(() -> new RuntimeException("Keyword not found"));
    }
}
