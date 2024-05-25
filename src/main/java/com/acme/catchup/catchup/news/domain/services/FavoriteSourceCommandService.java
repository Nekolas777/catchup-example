package com.acme.catchup.catchup.news.domain.services;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.domain.model.commands.CreateFavoriteSourceCommand;

import java.util.Optional;

public interface FavoriteSourceCommandService {

    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);

}
