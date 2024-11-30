package com.hackernews.backend.model.dao.clickhouse;

import java.util.List;
import java.util.Map;

public interface AnalyticsRepository {
    List<Map<String, Object>> findMedianConstructionPrices();
}


