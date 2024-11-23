package com.counterspell.yunjisang.counterspell.domain.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

public class ResponseUserDTO {
    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class RegisterUserDTO {
        private Long userId;
        private String email;
    }

    @Data
    @Setter(AccessLevel.NONE)
    @Builder
    public static class CreateJWTDTO {
        private String token;
    }
}
