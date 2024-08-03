package ru.effective_mobile.tinyurl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.effective_mobile.tinyurl.domain.entity.TinyUrl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class URLShorterService {

    @Value("${base.url}")
    private String baseUrl;
    private final TinyUrlService tinyUrlService;

    public String makeShortUrl(String original) {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        Optional<TinyUrl> originalByShorter = tinyUrlService.getOriginalByShorter(baseUrl + uuid);
        TinyUrl saved;
        if (originalByShorter.isEmpty()) {
            TinyUrl tinyUrl = new TinyUrl();
            tinyUrl.setShorter(baseUrl + uuid);
            tinyUrl.setOriginal(original);
            tinyUrl.setTtl(LocalDateTime.now().plusHours(1));
            saved = tinyUrlService.saveTiny(tinyUrl);
        } else {
            uuid = UUID.randomUUID().toString().substring(0, 8);
            TinyUrl tinyUrl = new TinyUrl();
            tinyUrl.setShorter(baseUrl + uuid);
            tinyUrl.setOriginal(original);
            tinyUrl.setTtl(LocalDateTime.now().plusHours(1));
            saved = tinyUrlService.saveTiny(tinyUrl);
        }
        return saved.getShorter();
    }

    public String makeShortUrl(String original, String alias) {
        Optional<TinyUrl> originalByShorter = tinyUrlService.getOriginalByShorter(baseUrl + alias);
        TinyUrl saved;
        if (originalByShorter.isEmpty()) {
            TinyUrl tinyUrl = new TinyUrl();
            tinyUrl.setShorter(baseUrl + alias);
            tinyUrl.setOriginal(original);
            tinyUrl.setTtl(LocalDateTime.now().plusHours(1));
            saved = tinyUrlService.saveTiny(tinyUrl);
            return saved.getShorter();
        } else {
            throw new IllegalArgumentException("Url with alias " + alias + " exist!");
        }
    }

}