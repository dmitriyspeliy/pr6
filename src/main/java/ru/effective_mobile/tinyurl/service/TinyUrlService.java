package ru.effective_mobile.tinyurl.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effective_mobile.tinyurl.domain.entity.TinyUrl;
import ru.effective_mobile.tinyurl.repository.TinyUrlRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TinyUrlService {

    private final TinyUrlRepository repository;

    public Optional<TinyUrl> getOriginalByShorter(String shorter) {
        log.info("Find url by shorter link");
        return repository.getTinyUrlsByShorter(shorter);
    }

    public TinyUrl saveTiny(TinyUrl tinyUrl) {
        TinyUrl save = repository.save(tinyUrl);
        log.info("Url was saved");
        return save;
    }

}
