package com.counterspell.yunjisang.counterspell.domain.ai.controller;

import com.counterspell.yunjisang.counterspell.domain.ai.service.AiService;
import com.counterspell.yunjisang.counterspell.global.common.ApiPath;
import com.counterspell.yunjisang.counterspell.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "AI CONVERSTATION API", description = "VIDEO를 AI 서버로 보내주는 API")
@RequestMapping(ApiPath.CONVERSATION_API_PATH)
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService; // Missing semicolon fixed

    @PostMapping
    public SuccessResponse getVideoFromClientAndSaveToLocal(@RequestPart(value = "file") MultipartFile file) {
        // Client로부터 비디오를 받아와서 AI 서버에 보내줌
        ResponseEntity<String> response = aiService.processFile(file);


        // 성공적으로 파일 업로드가 되면 SuccessResponse 반환
        if (response.getStatusCode().is2xxSuccessful()) {
            return new SuccessResponse(true, response.getBody());
        } else {
            return new SuccessResponse(false, response.getBody());
        }
    }
}
