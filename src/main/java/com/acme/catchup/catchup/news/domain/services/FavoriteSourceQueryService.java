package com.acme.catchup.catchup.news.domain.services;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.domain.model.queries.GetAllFavoriteSourceByNewsApiKeyQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

public interface FavoriteSourceQueryService {

    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);

    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);

    List<FavoriteSource> handle(GetAllFavoriteSourceByNewsApiKeyQuery query);

}
