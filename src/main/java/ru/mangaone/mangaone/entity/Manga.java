package ru.mangaone.mangaone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String author;
    private String artist;
    private String year;
    private int views;
    private double rating;

    @Lob
    private String image;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "manga_id"))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

}
