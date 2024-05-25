package com.acme.catchup.catchup.news.interfaces.rest;

import com.acme.catchup.catchup.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.catchup.news.domain.model.queries.GetAllFavoriteSourceByNewsApiKeyQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.catchup.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.catchup.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.catchup.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.catchup.news.interfaces.rest.resources.CreateFavoriteSourceResource;
import com.acme.catchup.catchup.news.interfaces.rest.resources.FavoriteSourceResource;
import com.acme.catchup.catchup.news.interfaces.rest.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.catchup.news.interfaces.rest.transform.FavoriteSourceResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/favorite-sources")
public class FavoriteSourcesController {

    private final FavoriteSourceCommandService favoriteSourceCommandService;

    private final FavoriteSourceQueryService favoriteSourceQueryService;

    public FavoriteSourcesController(FavoriteSourceCommandService favoriteSourceCommandService, FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResource resource) {

        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService.handle(CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resource));

        return favoriteSource.map(source -> new ResponseEntity<>(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(new GetFavoriteSourceByIdQuery(id));

        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApiKey(String newsApiKey) {

        var getAllFavoriteSourcesByNewsApiKeyQuery = new GetAllFavoriteSourceByNewsApiKeyQuery(newsApiKey);

        var favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourcesByNewsApiKeyQuery);

        if (favoriteSources.isEmpty()) return ResponseEntity.notFound().build();

        var favoriteSourceResources = favoriteSources.stream().map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(favoriteSourceResources);

    }

    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApiKeyAndSourceId(String newsApiKey, String sourceId) {

        var getFavoriteSourceByNewsApiKeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApiKey, sourceId);

        var favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApiKeyAndSourceIdQuery);

        if (favoriteSource.isEmpty()) return ResponseEntity.notFound().build();

        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<?> getFavoriteSourcesWithParameters(@RequestParam Map<String, String> parameters) {

        if (parameters.containsKey("newsApiKey") && parameters.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApiKeyAndSourceId(parameters.get("newsApiKey"), parameters.get("sourceId"));
        } else if (parameters.containsKey("newsApiKey")) {
            return getAllFavoriteSourcesByNewsApiKey(parameters.get("newsApiKey"));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
