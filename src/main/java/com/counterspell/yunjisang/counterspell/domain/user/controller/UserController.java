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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPath.USER_API_PATH)
@Tag(name = "user", description = "USER API")
public class UserController extends DefaultController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Operation(summary = "Create USER", description = "회원가입")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.RegisterUserDTO>> registerUser(@Valid @RequestBody RequestUserDTO.RegisterUserDTO registerUserDTO) {
        ResponseUserDTO.RegisterUserDTO dto = userService.save(registerUserDTO.toEntity(passwordEncoder));
        SuccessResponse<ResponseUserDTO.RegisterUserDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login User", description = "로그인")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.CreateJWTDTO>> createJWT(@Valid @RequestBody RequestUserDTO.CreateJWTDTO createJWTDTO) {
        ResponseUserDTO.CreateJWTDTO dto = userService.createJWT(createJWTDTO);
        SuccessResponse<ResponseUserDTO.CreateJWTDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }
}
