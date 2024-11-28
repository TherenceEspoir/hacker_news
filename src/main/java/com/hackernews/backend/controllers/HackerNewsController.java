package com.hackernews.backend.controllers;

import com.hackernews.backend.model.dto.HackerNewsDTO;
import com.hackernews.backend.model.entity.HackerNewsEntity;
import com.hackernews.backend.services.SQLHackerNewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class HackerNewsController {
    private final SQLHackerNewsService hackerNewsService;

    public HackerNewsController(SQLHackerNewsService hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    @GetMapping
    public List<HackerNewsDTO> getAllNews() {
        return hackerNewsService.getAllNews();
    }

    @GetMapping("/search")
    public List<HackerNewsDTO> getNewsByAuthor(@RequestParam("author") String author) {
        return hackerNewsService.getNewsByAuthor(author);
    }
}
