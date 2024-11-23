package com.counterspell.yunjisang.counterspell.domain.user.controller;

import com.counterspell.yunjisang.counterspell.domain.user.dto.RequestUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.dto.ResponseUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.service.UserService;
import com.counterspell.yunjisang.counterspell.global.common.ApiPath;
import com.counterspell.yunjisang.counterspell.global.common.DefaultController;
import com.counterspell.yunjisang.counterspell.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@OpenAPIDefinition(info = @Info(title = "COUNTER SPELL API", version = "0.0.1", description = "COUNTER SPELL API 명세서"))
public class UserController extends DefaultController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 사용중인 정보"),
    })
    public ResponseEntity<SuccessResponse<ResponseUserDTO.RegisterUserDTO>> registerUser(@Valid @RequestBody RequestUserDTO.RegisterUserDTO registerUserDTO) {
        ResponseUserDTO.RegisterUserDTO dto = userService.save(registerUserDTO.toEntity());
        SuccessResponse<ResponseUserDTO.RegisterUserDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 정보입니다")
    })
    public ResponseEntity<SuccessResponse<ResponseUserDTO.CreateJWTDTO>> createJWT(@Valid @RequestBody RequestUserDTO.CreateJWTDTO createJWTDTO) {
        ResponseUserDTO.CreateJWTDTO dto = userService.createJWT(createJWTDTO);
        SuccessResponse<ResponseUserDTO.CreateJWTDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }
}
