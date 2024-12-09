package com.hackernews.backend.model.dao.postgres;

import com.hackernews.backend.model.entity.HackerNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HackerNewsRepository extends JpaRepository<HackerNewsEntity, Long> {
    List<HackerNewsEntity> findAll();
    List<HackerNewsEntity> findByBy(String author);
}