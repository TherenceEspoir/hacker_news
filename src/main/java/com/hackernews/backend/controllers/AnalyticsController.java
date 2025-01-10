package com.hackernews.backend.controllers;

import com.clickhouse.client.internal.google.common.primitives.UnsignedLong;
import com.hackernews.backend.services.implementations.AnalyticsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/private/analytics")
@RequestMapping("/api/news/analytics")
public class AnalyticsController {

    private final AnalyticsServiceImpl analyticsService;

    public AnalyticsController(AnalyticsServiceImpl analyticsService) {
        this.analyticsService = analyticsService;
    }

    //Nombre de posts par type
    @GetMapping("/posts-by-type")
    public Map<String, Long> getPostsByType() {
        return analyticsService.countPostsByType();
    }

    //Nombre de posts par date
    @GetMapping("/posts-by-date")
    public Map<Integer, Map<Month, Long>> countPostsByYearAndMonth()
    {
        return analyticsService.countPostsByYearAndMonth();
    }

}
