package com.counterspell.yunjisang.counterspell.domain.video.entity;

import com.counterspell.yunjisang.counterspell.domain.user.entity.User;
import com.counterspell.yunjisang.counterspell.domain.word.Word;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video {
    @Id @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String videoName;
    private String videoPath;

    @ManyToOne(fetch = FetchType.LAZY) @Setter
    private Word words;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Video(String videoName, String videoPath, Word words, User user) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.words = words;
        this.user = user;
    }
}
