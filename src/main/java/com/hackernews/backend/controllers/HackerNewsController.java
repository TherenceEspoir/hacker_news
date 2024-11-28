package com.hackernews.backend.controllers;

import com.hackernews.backend.model.entity.HackerNewsDO;
import com.hackernews.backend.services.HackerNewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class HackerNewsController {
    private final HackerNewsService hackerNewsService;

    public HackerNewsController(HackerNewsService hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    @GetMapping
    public List<HackerNewsDO> getAllNews() {
        return hackerNewsService.getAllNews();
    }
}
