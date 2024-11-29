package com.hackernews.backend.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    private final JdbcTemplate clickhouseJdbcTemplate;

    public AnalyticsService(JdbcTemplate clickhouseJdbcTemplate) {
        this.clickhouseJdbcTemplate = clickhouseJdbcTemplate;
    }

    /**
     * Exemple: Obtenir les prix médians de construction par année.
     */
    public List<Map<String, Object>> getMedianConstructionPrices() {
        String query = """
            SELECT annee_signature, 
                   AVG(construction_prix_de_revient_median_au_m2_des_operations) AS avg_price_per_m2,
                   AVG(construction_prix_de_revient_median_des_operations_au_logement) AS avg_price_per_logement
            FROM analytics_db.construction_rehabilitation_data 
            GROUP BY annee_signature 
            ORDER BY annee_signature
        """;

        return clickhouseJdbcTemplate.queryForList(query);
    }
}
