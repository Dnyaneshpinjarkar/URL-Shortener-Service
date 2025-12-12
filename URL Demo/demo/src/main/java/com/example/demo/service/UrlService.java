package com.example.demo.service;

import com.example.demo.entity.Url;
import com.example.demo.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {

    @Value("${http://localhost:8080/}")
    private String baseUrl;

    @Autowired
    private UrlRepo repo;



    // Generate 6-character short code
    private String generateCode()
    {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++)
            sb.append(chars.charAt(r.nextInt(chars.length())));

        return sb.toString();
    }


    public Url createShortUrl(String originalUrl, LocalDateTime expiryAt)
    {
        Url url = new Url();

        url.setOriginalUrl(originalUrl);
        url.setExpiryAt(expiryAt);

        String code = generateCode();
        url.setShortCode(code);

        repo.save(url);
        return url;
    }

    public Url getUrl(String code)
    {
        Url url = repo.findByShortCode(code)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (url.getExpiryAt() != null && LocalDateTime.now().isAfter(url.getExpiryAt()))
            throw new RuntimeException("URL expired");

        // update analytics
        url.setClicks(url.getClicks() + 1);
        url.setLastAccessed(LocalDateTime.now());
        repo.save(url);

        return url;
    }

    public Url analytics(String code)
    {
        return repo.findByShortCode(code)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    public String buildShortUrl(String code)
    {
        return baseUrl + "/" + code;
    }
}
