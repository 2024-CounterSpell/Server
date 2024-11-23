package com.counterspell.yunjisang.counterspell.domain.ai.controller;

import com.counterspell.yunjisang.counterspell.global.common.ApiPath;
import com.counterspell.yunjisang.counterspell.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController @Tag(name = "AI CONVERSTATION API", description = "VIDEO를 AI 서버로 보내주는 API")
@RequestMapping(ApiPath.CONVERSATION_API_PATH)
@RequiredArgsConstructor
public class AiController {
    @PostMapping("/")
    public SuccessResponse getVideoFromClientAndSaveToLocal(MultipartFile file) {
        // Client로 부터 비디오를 받아와서 AI 서버에 보내줌

        return new SuccessResponse(true, file);
    }
}
