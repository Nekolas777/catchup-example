package com.acme.catchup.catchup.news.application.internal.queryservices;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.domain.model.queries.GetAllFavoriteSourceByNewsApiKeyQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.catchup.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.catchup.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.catchup.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {

    private  final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }

    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourceByNewsApiKeyQuery query){
        return favoriteSourceRepository.findAllByNewsApiKeyId(query.newsApiKey());
    }

    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query){
        return favoriteSourceRepository.findById(query.id());
    }

    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query){
        return favoriteSourceRepository.findByNewsApiKeyId(query.newsApiKey(), query.sourceId());
    }


}
