package ru.mangaone.mangaone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.entity.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
