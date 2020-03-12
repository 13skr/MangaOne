package ru.mangaone.mangaone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mangaone.mangaone.entity.Manga;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaBaseDto {

    private Long id;
    private String name;
    private String image;

    public MangaBaseDto(Manga manga) {
        this.id = manga.getId();
        this.name = manga.getName();
        this.image = manga.getImage();
    }

}
