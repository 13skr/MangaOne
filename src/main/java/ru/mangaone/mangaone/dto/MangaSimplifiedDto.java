package ru.mangaone.mangaone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.mangaone.mangaone.entity.Genre;
import ru.mangaone.mangaone.entity.Manga;
import ru.mangaone.mangaone.entity.Status;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaSimplifiedDto extends MangaBaseDto {

    private String author;
    private String artist;
    private String year;
    private int views;
    private double rating;
    private Status status;
    private List<Genre> genres;

    public MangaSimplifiedDto(Manga manga) {
        super(manga);
        this.author = manga.getAuthor();
        this.artist = manga.getArtist();
        this.year = manga.getYear();
        this.views = manga.getViews();
        this.rating = manga.getRating();
        this.status = manga.getStatus();
        this.genres = manga.getGenres();
    }

}
