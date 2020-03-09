package ru.mangaone.mangaone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.domain.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

}
