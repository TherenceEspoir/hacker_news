package com.hackernews.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

public record HackerNewsDTO(
        Long id,
        String type,
        String by,
        Timestamp time,
        String text,
        List<String> kids,
        String url,
        String title,
        Integer score
) {}