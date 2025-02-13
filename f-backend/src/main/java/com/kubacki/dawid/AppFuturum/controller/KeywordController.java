package com.kubacki.dawid.AppFuturum.controller;

import com.kubacki.dawid.AppFuturum.models.Keyword;
import com.kubacki.dawid.AppFuturum.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/all")
    public ResponseEntity<List<Keyword>> getAllKeywords() {
        List<Keyword> keywords = keywordService.getAllKeywords();
        return ResponseEntity.ok(keywords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keyword> getKeywordById(@PathVariable int id) {
        Keyword keyword = keywordService.getKeywordById(id);
        return ResponseEntity.ok(keyword);
    }
}
