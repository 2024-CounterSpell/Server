package com.counterspell.yunjisang.counterspell.domain.ai.controller;

import com.counterspell.yunjisang.counterspell.global.common.ApiPath;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Tag(name = "CONVERSTATION API", description = "VIDEO 변환 관련 API")
@RequestMapping(ApiPath.CONVERSATION_API_PATH)
@RequiredArgsConstructor
public class AiController {

}
