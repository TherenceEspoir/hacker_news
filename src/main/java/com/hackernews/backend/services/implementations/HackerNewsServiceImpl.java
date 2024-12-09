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



    /**
     * Retrieves all news items available in the repository.
     *
     * @return a list of HackerNewsDTO objects representing all news items
     */
    public List<HackerNewsDTO> getAllNews()
    {
        return hackerNewsRepository.findAll()
                .stream()
                .map(HackerNewsMapper::toDto)
                .toList();
    }



    /**
     * Retrieves news items authored by the specified author.
     *
     * @param author the name of the author whose news items are to be retrieved
     * @return a list of HackerNewsDTO objects representing the news items authored by the specified author
     */
    @Override
    public List<HackerNewsDTO> getNewsByAuthor(String author) {
        return hackerNewsRepository.findByBy(author)
                .stream()
                .map(HackerNewsMapper::toDto)
                .toList();

    }
}
