package com.hackernews.backend.services.interfaces;

import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    List<Map<String, Object>> getMedianConstructionPrices();
}
