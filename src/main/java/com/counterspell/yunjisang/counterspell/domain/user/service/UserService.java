package com.counterspell.yunjisang.counterspell.domain.user.service;

import com.counterspell.yunjisang.counterspell.domain.user.dto.RequestUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.dto.ResponseUserDTO;
import com.counterspell.yunjisang.counterspell.domain.user.entity.Role;
import com.counterspell.yunjisang.counterspell.domain.user.entity.User;
import com.counterspell.yunjisang.counterspell.domain.user.repository.UserRepository;
import com.counterspell.yunjisang.counterspell.global.exception.CustomException;
import com.counterspell.yunjisang.counterspell.global.exception.ErrorCode;
import com.counterspell.yunjisang.counterspell.global.utility.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseUserDTO.RegisterUserDTO save(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_EMAIL);
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(user);
        ResponseUserDTO.RegisterUserDTO registerUserDTO = ResponseUserDTO.RegisterUserDTO.builder()
                .userId(createdUser.getId())
                .email(createdUser.getEmail())
                .build();

        return registerUserDTO;
    }

    public ResponseUserDTO.CreateJWTDTO createJWT(RequestUserDTO.CreateJWTDTO createJWTDTO) {
        User user = findByEmail(createJWTDTO.getEmail());

        if (user == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(createJWTDTO.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(createJWTDTO.getEmail());
        return ResponseUserDTO.CreateJWTDTO.builder().token(token).build();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
