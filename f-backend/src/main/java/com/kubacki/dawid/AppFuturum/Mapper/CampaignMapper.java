package com.kubacki.dawid.AppFuturum.Mapper;

import com.kubacki.dawid.AppFuturum.Dto.CampaignDto;
import com.kubacki.dawid.AppFuturum.models.Campaign;
import com.kubacki.dawid.AppFuturum.models.Keyword;

import java.util.stream.Collectors;

public class CampaignMapper {
    public static CampaignDto toDto(Campaign campaign) {
        return new CampaignDto(
                campaign.getId(),
                campaign.getCampaignName(),
                campaign.getKeywords().stream().map(Keyword::getId).collect(Collectors.toSet()),
                campaign.getBidAmount(),
                campaign.getCampaignFund(),
                campaign.isStatus(),
                campaign.getTown().getId(),
                campaign.getRadius()
        );
    }
}
