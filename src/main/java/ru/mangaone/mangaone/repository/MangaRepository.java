package ru.mangaone.mangaone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.domain.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

}
