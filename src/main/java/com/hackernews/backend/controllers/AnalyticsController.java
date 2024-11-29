package com.hackernews.backend.controllers;

import com.hackernews.backend.services.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/median-construction-prices")
    public List<Map<String, Object>> getMedianConstructionPrices() {
        return analyticsService.getMedianConstructionPrices();
    }
}
