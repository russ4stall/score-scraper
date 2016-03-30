package com.russ4stall.fsp;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Russ Forstall
 */
public class NflScraper extends Scraper {
    public static final String BASE_URL = "http://www.nfl.com/";
    public static final String SCOREBOX_WRAPPER = "scorebox-wrapper";

    public NflScraper() {
        super(BASE_URL);
    }

    @Override
    public List<Score> scrapeScores(int seasonYear, int week) {
        Connection connection = Jsoup.connect(constructUrl(getUrl(), seasonYear, week));
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document.getElementsByClass("scorebox-wrapper")
                .stream()
                .map(NflScraper::parseScore)
                .collect(Collectors.toList());
    }

    public static Score parseScore(Element element) {
        Score score = new Score();
        score.setGameDate(element.select(".date").text());
        score.setAwayTeam(element.select(".away-team .team-name").text());
        score.setHomeTeam(element.select(".home-team .team-name").text());
        score.setAwayTeamScore(Integer.valueOf(element.select(".away-team .total-score").text()));
        score.setHomeTeamScore(Integer.valueOf(element.select(".home-team .total-score").text()));
        return score;
    }

    public static String constructUrl(String baseUrl, int year, int week) {
        return baseUrl + "scores/" + String.valueOf(year) + "/REG" + String.valueOf(week);
    }
}
