package com.example.demo.controller;


import com.example.demo.entity.Url;
import com.example.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;



    // POST: /shorten
    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(@RequestParam String url,
                                     @RequestParam(required = false) String expiry)
    {

        LocalDateTime expiryTime = expiry != null ? LocalDateTime.parse(expiry) : null;

        Url u = service.createShortUrl(url, expiryTime);

        return ResponseEntity.ok(service.buildShortUrl(u.getShortCode()));
    }

    // GET: /ABC123 → redirect
    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code)
    {
        Url url = service.getUrl(code);

        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, url.getOriginalUrl())
                .build();
    }

    // GET: /analytics/code
    @GetMapping("/analytics/{code}")
    public Url analytics(@PathVariable String code) {
        return service.analytics(code);
    }
}
