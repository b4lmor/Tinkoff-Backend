package edu.project3.entity;

public enum HttpMethod {
    DELETE,
    GET,
    HEAD,
    OPTIONS,
    PATCH,
    POST,
    PUT,
    TRACE;

    public static HttpMethod parse(String raw) {
        return switch (raw) {
            case "DELETE"  -> DELETE;
            case "GET"     -> GET;
            case "HEAD"    -> HEAD;
            case "OPTIONS" -> OPTIONS;
            case "PATCH"   -> PATCH;
            case "POST"    -> POST;
            case "PUT"     -> PUT;
            case "TRACE"   -> TRACE;
            default -> throw new IllegalArgumentException();
        };
    }

}
