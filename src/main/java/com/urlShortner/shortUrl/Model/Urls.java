package com.urlShortner.shortUrl.Model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "urls")
public class Urls {

    @Id
    private String id;

    private String longUrl;

    private String shortUrl;
}
