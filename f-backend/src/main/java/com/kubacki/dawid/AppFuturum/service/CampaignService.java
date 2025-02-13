package com.kubacki.dawid.AppFuturum.service;

import com.kubacki.dawid.AppFuturum.Dto.CampaignDto;
import com.kubacki.dawid.AppFuturum.Mapper.CampaignMapper;
import com.kubacki.dawid.AppFuturum.models.Campaign;
import com.kubacki.dawid.AppFuturum.models.Keyword;
import com.kubacki.dawid.AppFuturum.repositories.CampaignRepository;
import com.kubacki.dawid.AppFuturum.repositories.KeywordRepository;
import com.kubacki.dawid.AppFuturum.repositories.TownRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private KeywordRepository keywordRepository;

    public List<CampaignDto> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns.stream().map(CampaignMapper::toDto).collect(Collectors.toList());
    }

    public CampaignDto getCampaignById(int id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not found"));
        return CampaignMapper.toDto(campaign);
    }

    public Campaign createCampaign(CampaignDto campaignDto) {
        Set<Keyword> keywords = campaignDto.getKeywordId().stream().map(id -> keywordRepository.findById(id).orElseThrow(() -> new RuntimeException("Keyword not found")))
                .collect(Collectors.toSet());

        new Campaign();
        var campaign = Campaign.builder()
                .campaignName(campaignDto.getCampaignName())
                .campaignFund(campaignDto.getCampaignFund())
                .town(townRepository.findById(campaignDto.getTownId()).orElse(null))
                .bidAmount(campaignDto.getBidAmount())
                .radius(campaignDto.getRadius())
                .status(campaignDto.isStatus())
                .keywords(keywords)
                .build();

        return campaignRepository.save(campaign);

    }

    public Campaign updateCampaign(CampaignDto campaignDto) {
        Campaign campaign = campaignRepository.findById(campaignDto.getId()).orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setCampaignName(campaignDto.getCampaignName());
        campaign.setCampaignFund(campaignDto.getCampaignFund());
        campaign.setBidAmount(campaignDto.getBidAmount());
        campaign.setRadius(campaignDto.getRadius());
        campaign.setStatus(campaignDto.isStatus());
        campaign.setTown(townRepository.findById(campaignDto.getTownId()).orElse(null));
        campaign.setKeywords(campaignDto.getKeywordId().stream().map(id -> keywordRepository.findById(id).orElseThrow(() -> new RuntimeException("Keyword not found")))
                .collect(Collectors.toSet()));
        return campaignRepository.save(campaign);
    }

    @Transactional
    public void deleteCampaign(int id) {
        campaignRepository.deleteById(id);
    }
}
