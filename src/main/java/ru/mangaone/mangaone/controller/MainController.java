package ru.mangaone.mangaone.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.mangaone.mangaone.dto.MangaBaseDto;
import ru.mangaone.mangaone.dto.MangaSimplifiedDto;
import ru.mangaone.mangaone.entity.Manga;
import ru.mangaone.mangaone.repo.MangaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/manga")
public class MainController {

    private final MangaRepository mangaRepository;

    @Autowired
    public MainController(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    @GetMapping
    @Transactional
    public List<Manga> listManga() {
        return mangaRepository.findAll();
    }

    @GetMapping("/simple")
    @Transactional
    public List<MangaBaseDto> listMangaSimplified() {
        List<MangaBaseDto> result = new ArrayList<>();

        for (Manga manga:mangaRepository.findAll()) {
            result.add(new MangaBaseDto(manga));
        }
        return result;
    }

    @GetMapping("{id}")
    @Transactional
    public Manga getManga(@PathVariable("id") Manga manga) {
        return manga;
    }

    @GetMapping("{id}/simple")
    @Transactional
    public MangaSimplifiedDto getMangaSimplified(@PathVariable("id") Manga manga) {
        return new MangaSimplifiedDto(manga);
    }

    @PostMapping
    public Manga create(@RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    @PutMapping("{id}")
    public Manga updateManga(@PathVariable("id") Manga mangaFromDb, @RequestBody Manga manga) {
        BeanUtils.copyProperties(manga, mangaFromDb, "id");
        return mangaRepository.save(mangaFromDb);
    }

    @DeleteMapping("{id}")
    public int deleteManga(@PathVariable("id") Manga manga) {
        mangaRepository.delete(manga);
        return 1;
    }

}
