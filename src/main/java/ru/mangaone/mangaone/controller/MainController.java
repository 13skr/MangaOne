package ru.mangaone.mangaone.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mangaone.mangaone.domain.Manga;
import ru.mangaone.mangaone.repository.MangaRepository;

import java.util.List;

@RestController
@RequestMapping("/manga")
public class MainController {

    private final MangaRepository mangaRepository;

    @Autowired
    public MainController(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    @GetMapping
    public List<Manga> listManga() {
        return mangaRepository.findAll();
    }

    @GetMapping("{id}")
    public Manga getManga(@PathVariable("id") Manga manga) {
        return manga;
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
    public void deleteManga(@PathVariable("id") Manga manga) {
        mangaRepository.delete(manga);
    }

}
