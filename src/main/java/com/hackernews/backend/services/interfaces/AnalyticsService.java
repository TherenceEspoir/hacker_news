package com.hackernews.backend.services.interfaces;

import com.clickhouse.client.internal.google.common.primitives.UnsignedLong;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnalyticsService {

    //Méthode pour compter le nombre de posts par type
    Map<String, Long> countPostsByType();

    //Méthode pour récupérer la répartition des posts publiés par jour.
    Map<String, String> countPostsByDate();

}
