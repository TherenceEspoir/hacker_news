package com.hackernews.backend.services;

import com.hackernews.backend.model.dto.HackerNewsDTO;

import java.util.List;

public interface HackerNewsService {
    // Récupérer tous les news
    List<HackerNewsDTO> getAllNews();

    //Recuperer les news d'un auteur spécifique
    List<HackerNewsDTO> getNewsByAuthor(String author);

}
