package com.hackernews.backend.services.implementations;

import com.clickhouse.client.internal.google.common.primitives.UnsignedLong;
import com.hackernews.backend.services.interfaces.AnalyticsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private final JdbcTemplate clickhouseJdbcTemplate;

    public AnalyticsServiceImpl(@Qualifier("clickhouseJdbcTemplate") JdbcTemplate clickhouseJdbcTemplate) {
        this.clickhouseJdbcTemplate = clickhouseJdbcTemplate;
    }

    @Override
    public Map<String, Long> countPostsByType() {
        String query = """
        SELECT type, COUNT(*) AS count 
        FROM postgresql_hacker_news_materialised
        GROUP BY type
        ORDER BY count DESC
    """;

        List<Map<String, Object>> results = clickhouseJdbcTemplate.queryForList(query);

        // Transformer les résultats en Map
        return results.stream().collect(
                Collectors.toMap(
                        row -> (String) row.get("type"),
                        row -> ((Number) row.get("count")).longValue()
                )
        );
    }

    /*
    @Override
    public Map<LocalDate, Long> countPostsByDate() {
        String query = """
        SELECT toDate(time) AS post_date, COUNT(*) AS total_posts
        FROM postgresql_hacker_news_materialised
        GROUP BY post_date
        ORDER BY post_date
    """;

        List<Map<String, Object>> results = clickhouseJdbcTemplate.queryForList(query);

        // Transformer les résultats en Map
        return results.stream().collect(
                Collectors.toMap(
                        row -> LocalDate.parse((String) row.get("post_date")),
                        row -> ((Number) row.get("total_posts")).longValue()
                )
        );
    }
*/
    @Override
    public Map<String, String> countPostsByDate() {
        String query = """
        SELECT toDate(time) AS post_date, COUNT(*) AS total_posts
        FROM postgresql_hacker_news_materialised
        GROUP BY post_date
        ORDER BY post_date
    """;

        List<Map<String, Object>> results = clickhouseJdbcTemplate.queryForList(query);

        return results.stream().collect(
                Collectors.toMap(
                        row -> row.get("post_date").toString(), // Conversion directe en LocalDate
                        row -> row.get("total_posts").toString() // Pas de conversion en Long
                )
        );
    }


}
