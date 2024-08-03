package ru.effective_mobile.tinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.tinyurl.domain.entity.TinyUrl;

import java.util.Optional;

@Repository
public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {

    Optional<TinyUrl> getTinyUrlsByShorter(String shoter);
}
