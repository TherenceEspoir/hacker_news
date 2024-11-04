package com.hackernews.backend.DAO;

import com.hackernews.backend.DO.HackerNewsDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackerNewsRepository extends JpaRepository<HackerNewsDO, Long> {
}
