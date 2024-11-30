package com.hackernews.backend.services.implementations;

import com.hackernews.backend.model.dao.postgres.HackerNewsRepository;
import com.hackernews.backend.model.dto.HackerNewsDTO;
import com.hackernews.backend.model.mapper.HackerNewsMapper;
import com.hackernews.backend.services.interfaces.HackerNewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackerNewsServiceImpl implements HackerNewsService {
    private final HackerNewsRepository hackerNewsRepository;

    public HackerNewsServiceImpl(HackerNewsRepository hackerNewsRepository) {
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
        return hackerNewsRepository.findByBy(author)
                .stream()
                .map(HackerNewsMapper::toDto)
                .toList();

    }
}
