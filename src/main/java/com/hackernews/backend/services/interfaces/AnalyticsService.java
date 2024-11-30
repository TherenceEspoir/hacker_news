package com.hackernews.backend.services.interfaces;

import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    public List<Map<String, Object>> getMedianConstructionPrices();
}
