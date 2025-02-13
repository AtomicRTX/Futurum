package com.kubacki.dawid.AppFuturum.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import javax.validation.constraints.Min;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Campaigns")

public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String campaignName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "campaign_keyword",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    @NotNull
    private Set<Keyword> keywords;

    @Column(nullable = false)
    @Min(1)
    private Double bidAmount;

    @Column(nullable = false)
    private Double campaignFund;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @Column(nullable = false)
    private Double radius;
}
