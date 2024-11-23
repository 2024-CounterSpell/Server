package com.counterspell.yunjisang.counterspell.domain.word;

import com.counterspell.yunjisang.counterspell.domain.video.entity.Video;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word {
    @Id @Column(name = "word_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String wordName;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos;

    public void addVideo(Video video) {
        video.setWords(this);
        this.videos.add(video);
    }

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    @Builder
    public Word(String wordName, Difficulty difficulty, List<Video> videos) {
        this.wordName = wordName;
        this.difficulty = difficulty;
        this.videos = videos;
    }
}
