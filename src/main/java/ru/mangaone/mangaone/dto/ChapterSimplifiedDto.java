package ru.mangaone.mangaone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mangaone.mangaone.entity.Chapter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterSimplifiedDto {

    private Long id;
    private int number;
    private String name;

    public ChapterSimplifiedDto(Chapter chapter) {
        this.id = chapter.getId();
        this.number = chapter.getNumber();
        this.name = chapter.getName();
    }
}
