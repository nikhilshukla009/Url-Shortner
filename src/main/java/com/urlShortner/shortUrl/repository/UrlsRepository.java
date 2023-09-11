package com.urlShortner.shortUrl.repository;

import com.urlShortner.shortUrl.Model.Urls;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlsRepository extends MongoRepository<Urls, String> {
    Optional<Urls> findByLongUrl(String longUrl);

    Optional<Urls> findByShortUrl(String shortUrl);
}
