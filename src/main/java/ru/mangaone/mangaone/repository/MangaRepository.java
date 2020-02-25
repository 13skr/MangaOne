package ru.mangaone.mangaone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.domain.Genre;
import ru.mangaone.mangaone.domain.Manga;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

    Manga findMangaById(Long id);
    void deleteMangaById(Long id);
}
