package com.hackernews.backend.services;

import com.hackernews.backend.model.dao.postgres.HackerNewsRepository;
import com.hackernews.backend.model.dto.HackerNewsDTO;
import com.hackernews.backend.model.mapper.HackerNewsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SQLHackerNewsService implements HackerNewsService {
    private final HackerNewsRepository hackerNewsRepository;


    public SQLHackerNewsService(HackerNewsRepository hackerNewsRepository) {
        this.hackerNewsRepository = hackerNewsRepository;
    }

    public List<HackerNewsDTO> getAllNews()
    {
        return hackerNewsRepository.findAll()
                .stream()
                .map(HackerNewsMapper::toDto)
                .toList();
    }

    /**
     * @param author 
     * @return
     */
    @Override
    public List<HackerNewsDTO> getNewsByAuthor(String author) {
        return hackerNewsRepository.findAll()
                .stream()
                .map(HackerNewsMapper::toDto)
                .filter(news->news.by().equalsIgnoreCase(author))
                .toList();

    }
}
