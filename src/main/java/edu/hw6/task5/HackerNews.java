package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LONG_ARRAY_STRING_PATTERN = "\\[\\d+(,\\d+)*\\]";
    private static final String LONG_ARRAY_STRING_SEPARATOR = ",";
    private static final String BASE_ITEM_URL
        = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final String BASE_STORIES_URL
        = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final int REQUEST_DURATION = 10;

    public String news(long id) {
        try (HttpClient client = newHttpClient()) {

            HttpRequest request = httpRequestOf(
                new URI(BASE_ITEM_URL.formatted(id))
            );

            var response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            String body = response.body();

            return parseTitle(body);

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "";
        }
    }

    public long[] hackerNewsTopStories() {

        try (HttpClient client = newHttpClient()) {

            HttpRequest request = httpRequestOf(
                new URI(BASE_STORIES_URL)
            );

            var response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            String body = response.body();

            return parseStringToLongArray(body);

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new long[0];
        }
    }

    private long[] parseStringToLongArray(String rawLongArray) {
        if (isNotValidRawLongArray(rawLongArray)) {
            return new long[0];
        }

        return Arrays.stream(
            rawLongArray.substring(
                    1,
                    rawLongArray.length() - 1
                )
                .split(LONG_ARRAY_STRING_SEPARATOR)
            )
            .mapToLong(Long::valueOf)
            .toArray();
    }

    private boolean isNotValidRawLongArray(String rawLongArray) {
        return !rawLongArray.matches(LONG_ARRAY_STRING_PATTERN);
    }

    private HttpRequest httpRequestOf(URI uri) {
        return HttpRequest.newBuilder()
            .uri(uri)
            .header("AcceptEncoding", "gzip")
            .timeout(Duration.of(REQUEST_DURATION, ChronoUnit.SECONDS))
            .build();
    }

    private String parseTitle(String json) {
        String pattern = "\"title\"\\s*:\\s*\"([^\"]+)\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(json);

        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }
}
