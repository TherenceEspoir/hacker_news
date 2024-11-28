package com.hackernews.backend.model.mapper;

import com.hackernews.backend.model.dto.HackerNewsDTO;
import com.hackernews.backend.model.entity.HackerNewsEntity;

import java.util.List;

public class HackerNewsMapper {

    public static HackerNewsDTO toDto(final HackerNewsEntity entity)
    {
        return new HackerNewsDTO(
                    entity.getId(),
                    entity.getType(),
                    entity.getBy(),
                    entity.getTime(),
                    entity.getText(),
                    List.of(entity.getKids()),
                    entity.getUrl(),
                    entity.getTitle(),
                    entity.getScore());
    }
}
