package ru.mangaone.mangaone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.entity.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

}
