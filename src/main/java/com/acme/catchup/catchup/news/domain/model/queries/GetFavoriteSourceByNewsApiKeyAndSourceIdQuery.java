package com.acme.catchup.catchup.news.domain.model.queries;

public record GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(String newsApiKey, String sourceId) {

    public GetFavoriteSourceByNewsApiKeyAndSourceIdQuery {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey is required");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId is required");
        }
    }

}
