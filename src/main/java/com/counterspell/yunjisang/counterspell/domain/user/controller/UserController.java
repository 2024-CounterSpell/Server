package com.counterspell.yunjisang.counterspell.domain.user.controller;

import com.counterspell.yunjisang.counterspell.domain.user.dto.RequestUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.dto.ResponseUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.service.UserService;
import com.counterspell.yunjisang.counterspell.global.common.ApiPath;
import com.counterspell.yunjisang.counterspell.global.common.DefaultController;
import com.counterspell.yunjisang.counterspell.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPath.USER_API_PATH)
@Tag(name = "USER API", description = "사용자 관련 API")
public class UserController extends DefaultController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "사용자 회원가입")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.RegisterUserDTO>> registerUser(@Valid @RequestBody RequestUserDTO.RegisterUserDTO registerUserDTO) {
        ResponseUserDTO.RegisterUserDTO dto = userService.save(registerUserDTO.toEntity());
        SuccessResponse<ResponseUserDTO.RegisterUserDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.CreateJWTDTO>> createJWT(@Valid @RequestBody RequestUserDTO.CreateJWTDTO createJWTDTO) {
        ResponseUserDTO.CreateJWTDTO dto = userService.createJWT(createJWTDTO);
        SuccessResponse<ResponseUserDTO.CreateJWTDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }
}
