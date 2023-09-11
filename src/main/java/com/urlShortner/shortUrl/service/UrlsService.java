package com.urlShortner.shortUrl.service;

import com.urlShortner.shortUrl.Model.Urls;
import com.urlShortner.shortUrl.repository.UrlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlsService {
    private static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

//    private long uniqueIdCounter = 1;


    @Autowired
    private UrlsRepository urlsRepository;

    public String shortenUrl(String longUrl) {
        Optional<Urls> existingMapping = urlsRepository.findByLongUrl(longUrl);
        if (existingMapping.isPresent()) {
            return existingMapping.get().getShortUrl();
        }

        String shortUrl = generateShortUrl();

        Urls url = new Urls();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        urlsRepository.save(url);

        return shortUrl;
    }


    private String generateShortUrl() {
        long id = urlsRepository.count() + 1;
        StringBuilder shortUrl = new StringBuilder();
        long base = BASE62_CHARACTERS.length();

        while (id > 0) {
            shortUrl.insert(0, BASE62_CHARACTERS.charAt((int) (id % base)));
            id /= base;
        }
        return shortUrl.toString();
    }


//    private synchronized long getNextUniqueId() {
//        return uniqueIdCounter++;
//    }

    public String expandUrl(String shortUrl) {
        Optional<Urls> mapping = urlsRepository.findByShortUrl(shortUrl);
        return mapping.map(Urls::getLongUrl).orElse(null);
    }
}