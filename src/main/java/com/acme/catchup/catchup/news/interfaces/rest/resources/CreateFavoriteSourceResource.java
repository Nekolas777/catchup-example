package com.acme.catchup.catchup.news.interfaces.rest.resources;

public record CreateFavoriteSourceResource(String newsApiKeyId, String sourceId) {
    public CreateFavoriteSourceResource {
        if (newsApiKeyId == null || newsApiKeyId.isBlank()) {
            throw new IllegalArgumentException("newsApiKeyId cannot be null or empty");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId cannot be null or empty");
        }
    }

}
