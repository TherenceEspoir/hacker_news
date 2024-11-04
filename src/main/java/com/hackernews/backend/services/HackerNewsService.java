package com.hackernews.backend.services;

import com.hackernews.backend.DAO.HackerNewsRepository;
import com.hackernews.backend.DO.HackerNewsDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackerNewsService {
    private final HackerNewsRepository hackerNewsRepository;

    public HackerNewsService(HackerNewsRepository hackerNewsRepository) {
        this.hackerNewsRepository = hackerNewsRepository;
    }

    public List<HackerNewsDO> getAllNews()
    {
        return hackerNewsRepository.findAll();
    }
}
