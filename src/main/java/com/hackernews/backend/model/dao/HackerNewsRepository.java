package com.hackernews.backend.model.dao;

import com.hackernews.backend.model.entity.HackerNewsDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackerNewsRepository extends JpaRepository<HackerNewsDO, Long> {
}
