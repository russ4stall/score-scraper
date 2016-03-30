package com.russ4stall.fsp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Russ Forstall
 */
public class ScoreScraperTest {
    private Scraper scraper;

    public ScoreScraperTest() {
        scraper = new NflScraper();
    }

    @Test
    public void testNflConnect() {
        assertTrue("Failed to connect to " + scraper.getUrl(), scraper.isAvailable());
    }

    @Test
    public void testConstructNflUrl() {
        assertEquals(NflScraper.constructUrl(NflScraper.BASE_URL, 2012, 3), "http://www.nfl.com/scores/2012/REG3");
        assertEquals(NflScraper.constructUrl(NflScraper.BASE_URL, 2014, 6), "http://www.nfl.com/scores/2014/REG6");
        assertEquals(NflScraper.constructUrl(NflScraper.BASE_URL, 2001, 15), "http://www.nfl.com/scores/2001/REG15");
        assertEquals(NflScraper.constructUrl(NflScraper.BASE_URL, 2045, 60), "http://www.nfl.com/scores/2045/REG60");
    }

    @Test
    public void testParseScore() {
        Score expectedScore = new Score();
        expectedScore.setAwayTeam("Jaguars");
        expectedScore.setHomeTeam("Colts");
        expectedScore.setAwayTeamScore(22);
        expectedScore.setHomeTeamScore(17);
        expectedScore.setGameDate("Sun, Sep 23");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testParseScore.html").getFile());
        assertNotNull(file);
        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(doc);
        Element element = doc.getElementsByClass(NflScraper.SCOREBOX_WRAPPER).first();

        Score parsedScore = NflScraper.parseScore(element);
        assertTrue(parsedScore.equals(expectedScore));
    }

    @Test
    public void testScrapeScores() {
        Score eaglesScore = new Score();
        eaglesScore.setAwayTeam("Eagles");
        eaglesScore.setHomeTeam("Cardinals");
        eaglesScore.setAwayTeamScore(6);
        eaglesScore.setHomeTeamScore(27);
        eaglesScore.setGameDate("Sun, Sep 23");

        Score scrapedEaglesScore =
                scraper.scrapeScores(2012, 3)
                        .stream()
                        .filter(score -> score.getAwayTeam().equals("Eagles"))
                        .findFirst()
                        .get();

        assertTrue(scrapedEaglesScore.equals(eaglesScore));
    }
}
