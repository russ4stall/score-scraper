package com.russ4stall.fsp;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Russ Forstall
 */
public abstract class Scraper {
    private final String BASE_URL;

    public Scraper(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    public boolean isAvailable() {
        try {
            URL myURL = new URL(BASE_URL);
            URLConnection urlConnection = myURL.openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public abstract List<Score> scrapeScores(int seasonYear, int week);

    public String getUrl() {
        return BASE_URL;
    }
}
