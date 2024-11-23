package com.counterspell.yunjisang.counterspell.domain.user.controller;

import com.counterspell.yunjisang.counterspell.domain.user.dto.RequestUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.dto.ResponseUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.service.UserService;
import com.counterspell.yunjisang.counterspell.global.common.DefaultController;
import com.counterspell.yunjisang.counterspell.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends DefaultController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.RegisterUserDTO>> registerUser(@RequestBody RequestUserDTO.RegisterUserDTO registerUserDTO) {
        ResponseUserDTO.RegisterUserDTO dto = userService.save(registerUserDTO.toEntity());
        SuccessResponse<ResponseUserDTO.RegisterUserDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<ResponseUserDTO.CreateJWTDTO>> createJWT(@RequestBody RequestUserDTO.CreateJWTDTO createJWTDTO) {
        ResponseUserDTO.CreateJWTDTO dto = userService.createJWT(createJWTDTO);
        SuccessResponse<ResponseUserDTO.CreateJWTDTO> response = new SuccessResponse<>(true, dto);

        return new ResponseEntity<>(response, createHttpHeaders(), HttpStatus.CREATED);
    }
}
