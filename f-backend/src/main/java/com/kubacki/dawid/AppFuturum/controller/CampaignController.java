package com.kubacki.dawid.AppFuturum.controller;

import com.kubacki.dawid.AppFuturum.Dto.CampaignDto;
import com.kubacki.dawid.AppFuturum.models.Campaign;
import com.kubacki.dawid.AppFuturum.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")

public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/all")
    public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
        List<CampaignDto> campaigns = campaignService.getAllCampaigns();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> getCampaignById(@PathVariable int id) {
        CampaignDto campaign = campaignService.getCampaignById(id);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignDto campaignDto) {
        Campaign campaignSaved = campaignService.createCampaign(campaignDto);
        return new ResponseEntity<>(campaignSaved, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Campaign> editCampaign(@RequestBody CampaignDto campaignDto) {
        Campaign campaignSaved = campaignService.updateCampaign(campaignDto);
        return new ResponseEntity<>(campaignSaved, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteCampaign(@PathVariable int id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok(id);
    }
}
