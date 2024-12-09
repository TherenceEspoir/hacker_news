package com.hackernews.backend.controllers;

import com.hackernews.backend.model.dto.HackerNewsDTO;
import com.hackernews.backend.services.implementations.HackerNewsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class HackerNewsController {
    private final HackerNewsServiceImpl hackerNewsService;

    public HackerNewsController(HackerNewsServiceImpl hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    @GetMapping
    public List<HackerNewsDTO> getAllNews() {
        return hackerNewsService.getAllNews();
    }

    /**
     * Retrieves a list of news items authored by the specified author.
     *
     * @param author the name of the author whose news items are to be retrieved
     * @return a list of HackerNewsDTO objects authored by the specified author
     */
    @GetMapping("/search")
    public List<HackerNewsDTO> getNewsByAuthor(@RequestParam("author") String author) {
        return hackerNewsService.getNewsByAuthor(author);
    }
}
