package com.acme.catchup.catchup.news.application.internal.commandservices;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.catchup.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.catchup.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class FavoriteSourceCommandServiceImpl implements FavoriteSourceCommandService {

    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceCommandServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {

        this.favoriteSourceRepository = favoriteSourceRepository;

    }

    @Override
    public Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command){

        if( favoriteSourceRepository.existsByNewsApiKeyIdAndSourceId(command.newsApiKeyId(), command.sourceId())){

            throw new IllegalArgumentException("Favorite source with same source ID already exists for this API key");

        }

        var favoriteSource = new FavoriteSource(command);

        var createdFavoriteSource = favoriteSourceRepository.save(favoriteSource);

        return Optional.of(createdFavoriteSource);

    }


}
