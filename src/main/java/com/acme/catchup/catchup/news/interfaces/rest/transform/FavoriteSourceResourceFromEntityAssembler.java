package com.acme.catchup.catchup.news.interfaces.rest.transform;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.interfaces.rest.resources.FavoriteSourceResource;

public class FavoriteSourceResourceFromEntityAssembler {

    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity) {
        return new FavoriteSourceResource(entity.getId(), entity.getNewsApiKeyId(), entity.getSourceId());
    }

}
