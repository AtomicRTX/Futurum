package com.kubacki.dawid.AppFuturum.repositories;

import com.kubacki.dawid.AppFuturum.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
