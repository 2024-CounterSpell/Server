package com.counterspell.yunjisang.counterspell.domain.user.dto;

import com.counterspell.yunjisang.counterspell.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RequestUserDTO {
    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class RegisterUserDTO {
        @NotBlank(message = "이메일은 필수값입니다") private String email;
        @NotBlank(message = "이름은 필수값입니다") private String username;
        private String password;

        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .email(email)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build();
        }
    }

    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class CreateJWTDTO {
        @NotBlank(message = "이메일은 필수값입니다") private String email;
        private String password;
    }
}
