package edu.project3.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int HTTP_STATUS_SUCCESS = 200;

    private HttpUtils() {
    }

    public static void saveHttpRequestToFile(String url, String filePath)
        throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<Path> httpResponse = httpClient.send(
            httpRequest,
            HttpResponse.BodyHandlers.ofFile(Paths.get(filePath))
        );

        if (httpResponse.statusCode() == HTTP_STATUS_SUCCESS) {
            LOGGER.info("Request successful! Logs are downloaded.");

        } else {
            LOGGER.error("Request failed with status code: " + httpResponse.statusCode());
        }
    }
}
