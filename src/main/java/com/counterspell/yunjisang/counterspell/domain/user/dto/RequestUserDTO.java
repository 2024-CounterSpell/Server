package com.counterspell.yunjisang.counterspell.domain.user.dto;

import com.counterspell.yunjisang.counterspell.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

public class RequestUserDTO {
    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class RegisterUserDTO {
        private String email;
        private String username;
        private String password;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .build();
        }
    }

    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class CreateJWTDTO {
        private String email;
        private String password;
    }
}
