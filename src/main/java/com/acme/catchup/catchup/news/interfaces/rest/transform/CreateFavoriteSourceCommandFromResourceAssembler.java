package com.acme.catchup.catchup.news.interfaces.rest.transform;

import com.acme.catchup.catchup.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.catchup.news.interfaces.rest.resources.CreateFavoriteSourceResource;

public class CreateFavoriteSourceCommandFromResourceAssembler {

    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResource resource) {
        return new CreateFavoriteSourceCommand(resource.newsApiKeyId(), resource.sourceId());
    }
}
