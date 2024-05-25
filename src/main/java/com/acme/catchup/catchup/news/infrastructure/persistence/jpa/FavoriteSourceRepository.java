package com.acme.catchup.catchup.news.infrastructure.persistence.jpa;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {

    // @Query("SELECT fs FROM FavoriteSource fs WHERE fs.newsApiKeyId = :newsApiKey")
    List<FavoriteSource> findAllByNewsApiKeyId(String newsApiKey);

    boolean existsByNewsApiKeyIdAndSourceId(String newsApiKey, String sourceId);

    Optional<FavoriteSource> findByNewsApiKeyId(String newsApiKey, String sourceId);

}
