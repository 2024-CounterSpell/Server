package com.counterspell.yunjisang.counterspell.domain.ai.service;

import com.counterspell.yunjisang.counterspell.domain.video.entity.Video;
import com.counterspell.yunjisang.counterspell.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AiService {
    private final VideoRepository videoRepository;

    // 실행 중인 경로 기준으로 업로드 경로 설정
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/uploads/";

    public ResponseEntity<String> processFile(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 비어 있습니다.");
        }

        try {
            // 업로드 디렉터리가 없으면 생성
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();  // 디렉토리 생성
            }

            // 고유한 파일 이름 생성
            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + uniqueFileName;  // 파일 저장 경로

            // 파일을 지정한 경로로 저장
            File dest = new File(filePath);
            file.transferTo(dest);  // 파일 저장

            Video video = Video.builder()
                    .videoName(uniqueFileName)
                    .videoPath(filePath)
                    .build();

            videoRepository.save(video);

            return ResponseEntity.ok("업로드 성공: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();  // 예외 출력
            return ResponseEntity.status(500).body("파일 업로드 실패: " + e.getMessage());
        }
    }
}
