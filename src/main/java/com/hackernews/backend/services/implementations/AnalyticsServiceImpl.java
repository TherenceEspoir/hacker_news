package com.hackernews.backend.services.implementations;

import com.clickhouse.client.internal.google.common.primitives.UnsignedLong;
import com.hackernews.backend.services.interfaces.AnalyticsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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


    @Override
    public Map<LocalDate, UnsignedLong> countPostsByDate() {
        String query = """
        SELECT toDate(time) AS post_date, COUNT(*) AS total_posts
        FROM postgresql_hacker_news_materialised
        GROUP BY post_date
        ORDER BY post_date
    """;

        List<Map<String, Object>> results = clickhouseJdbcTemplate.queryForList(query);
        return results.stream().collect(
                Collectors.toMap(
                        row -> (LocalDate) row.get("post_date"),
                        row -> UnsignedLong.valueOf(((Number) row.get("total_posts")).longValue())
                )
        );
    }


    @Override
    public Map<Integer, Map<Month, Long>> countPostsByYearAndMonth() {
        String query = """
        SELECT toYear(time) AS year, toMonth(time) AS month, COUNT(*) AS total_posts
        FROM postgresql_hacker_news_materialised
        GROUP BY year, month
        ORDER BY year, month
    """;

        List<Map<String, Object>> results = clickhouseJdbcTemplate.queryForList(query);

        Map<Integer, Map<Month, Long>> postCountByYearAndMonth = new TreeMap<>();

        for (Map<String, Object> row : results) {
            int year = ((Number) row.get("year")).intValue();
            Month month = Month.of(((Number) row.get("month")).intValue());
            long count = ((Number) row.get("total_posts")).longValue();

            postCountByYearAndMonth.computeIfAbsent(year, k -> new EnumMap<>(Month.class)).put(month, count);
        }

        return postCountByYearAndMonth;
    }

}
