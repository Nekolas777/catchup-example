package com.acme.catchup.catchup.news.domain.model.commands;

/**
 * Command to create a favorite news source.
 * @param newsApiKeyId the news API key ID
 * @param sourceId the source ID
 * @throws IllegalArgumentException if newsApiKeyId is null or empty
 */
public record CreateFavoriteSourceCommand(String newsApiKeyId       , String sourceId) {

    public CreateFavoriteSourceCommand {
        if (newsApiKeyId == null || newsApiKeyId.isBlank()) {
            throw new IllegalArgumentException("newsApiKeyId cannot be null or empty");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId cannot be null or empty");
        }
    }

}
