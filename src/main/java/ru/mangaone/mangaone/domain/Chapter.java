package ru.mangaone.mangaone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    @JsonIgnoreProperties("chapters")
    private Manga manga;

    private int number;

    private String name;

    @OneToMany(targetEntity = Page.class, mappedBy = "chapter",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Page> pages;

}
