package ru.mangaone.mangaone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.entity.Chapter;
import ru.mangaone.mangaone.entity.Manga;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findAllByManga(Manga manga);
}
