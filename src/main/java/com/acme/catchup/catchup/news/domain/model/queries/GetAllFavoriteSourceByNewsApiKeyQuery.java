package com.acme.catchup.catchup.news.domain.model.queries;

public record GetAllFavoriteSourceByNewsApiKeyQuery(String newsApiKey) {

    public GetAllFavoriteSourceByNewsApiKeyQuery {

        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey is required");
        }

    }

}
