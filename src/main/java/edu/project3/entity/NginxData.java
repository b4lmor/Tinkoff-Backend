package edu.project3.entity;

import java.time.LocalDateTime;

public record NginxData(
    String ipAddress,
    LocalDateTime time,
    HttpMethod method,
    String requestUri,
    String httpVersion,
    int statusCode,
    int bytesSent
) {
}
