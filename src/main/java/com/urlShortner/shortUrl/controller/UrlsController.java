package com.urlShortner.shortUrl.controller;


import com.urlShortner.shortUrl.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UrlsController {

    @Autowired
    private UrlsService urlsService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody String longUrl){
        String shortUrl = urlsService.shortenUrl(longUrl);
        return ResponseEntity.ok("localhost:8080/"+shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> expand(@PathVariable String shortUrl) {
        String longUrl = urlsService.expandUrl(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", longUrl)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
