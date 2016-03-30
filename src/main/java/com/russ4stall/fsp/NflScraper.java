package com.russ4stall.fsp;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Russ Forstall
 */
public class NflScraper extends Scraper {
    public static final String BASE_URL = "http://www.nfl.com/";

    public NflScraper() {
        super(BASE_URL);
    }

    @Override
    public List<Score> scrapeScores(int seasonYear, int week) {
        Jsoup.connect(constructUrl(getUrl(), seasonYear, week));

        return new ArrayList<>();
    }

    public static String constructUrl(String baseUrl, int year, int week) {
        return baseUrl + "scores/" + String.valueOf(year) + "/REG" + String.valueOf(week);
    }
}
