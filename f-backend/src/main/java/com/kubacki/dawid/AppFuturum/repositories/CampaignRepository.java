package com.kubacki.dawid.AppFuturum.repositories;


import com.kubacki.dawid.AppFuturum.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
}
