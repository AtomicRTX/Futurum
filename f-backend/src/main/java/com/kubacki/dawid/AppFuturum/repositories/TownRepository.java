package com.kubacki.dawid.AppFuturum.repositories;

import com.kubacki.dawid.AppFuturum.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
}
