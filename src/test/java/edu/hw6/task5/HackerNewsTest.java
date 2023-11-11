package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HackerNewsTest {

    private static final long ID = 38221386;
    private static final String EXPECTED_TOPIC
        = "Japan begins third release of treated Fukushima wastewater";

    @Test
    void testHackerNews() {

        HackerNews hackerNews = new HackerNews();

        long[] IDs = hackerNews.hackerNewsTopStories();
        String topic = hackerNews.news(ID);

        assertTrue(
            Arrays.stream(IDs).anyMatch(v -> v == ID)
        );

        assertEquals(
            EXPECTED_TOPIC,
            topic
        );

    }
}
