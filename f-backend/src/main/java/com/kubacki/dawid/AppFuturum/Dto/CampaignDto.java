package com.kubacki.dawid.AppFuturum.Dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignDto {
    private Integer id;
    private String campaignName;
    private Set<Integer> keywordId;
    private Double bidAmount;
    private Double campaignFund;
    private boolean status;
    private Integer townId;
    private Double radius;
}
