package ru.effective_mobile.tinyurl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.tinyurl.service.URLShorterService;

@RequestMapping("test")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TinyController {

    private final URLShorterService tinyUrlService;

    @GetMapping
    public String testLink() {
        return "The link was successfully shortened";
    }

    @GetMapping("get-short")
    public String getShort(@RequestParam("link") String originalLink) {
        return tinyUrlService.makeShortUrl(originalLink);
    }

    @GetMapping("get-short-with-alias")
    public String getShort(@RequestParam("link") String originalLink,
                           @RequestParam("alias") String alias) {
        return tinyUrlService.makeShortUrl(originalLink, alias);
    }

}
