package ru.mangaone.mangaone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mangaone.mangaone.dto.ChapterSimplifiedDto;
import ru.mangaone.mangaone.entity.Chapter;
import ru.mangaone.mangaone.entity.Manga;
import ru.mangaone.mangaone.entity.Page;
import ru.mangaone.mangaone.repo.ChapterRepository;
import ru.mangaone.mangaone.repo.MangaRepository;
import ru.mangaone.mangaone.repo.PageRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    private final ChapterRepository chapterRepository;
    private final MangaRepository mangaRepository;
    private final PageRepository pageRepository;

    @Autowired
    public ChapterController(ChapterRepository chapterRepository,
                             MangaRepository mangaRepository,
                             PageRepository pageRepository) {
        this.chapterRepository = chapterRepository;
        this.mangaRepository = mangaRepository;
        this.pageRepository = pageRepository;
    }

    @GetMapping("/simple/{id}")
    public List<ChapterSimplifiedDto> listChapterSimplified(@PathVariable("id") Manga manga) {
        List<ChapterSimplifiedDto> result = new ArrayList<>();

        for (Chapter chapter:chapterRepository.findAllByManga(manga)) {
            result.add(new ChapterSimplifiedDto(chapter));
        }

        return result;
    }

    @GetMapping("{id}")
    public Chapter getChapter(@PathVariable("id") Chapter chapter) {
        return chapter;
    }

    @PostMapping()
    public Chapter createChapter(@RequestBody Manga manga) {
        List<Chapter> chapters = manga.getChapters();
        Chapter result = chapterRepository.save(chapters.get(chapters.size() - 1));
        mangaRepository.save(manga);

        return result;
    }

    @DeleteMapping("{id}")
    public int deleteChapter(@PathVariable("id") Chapter chapter) {
        chapterRepository.delete(chapter);
        return 1;
    }

    @PostMapping(value = "/pages")
    public List<Page> createPages(@RequestBody List<Page> pages) {
        return pageRepository.saveAll(pages);
    }

}
