package com.counterspell.yunjisang.counterspell.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTERED_EMAIL(HttpStatus.CONFLICT, "ACCOUNT-001", "이미 사용 중인 이메일입니다"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ACCOUNT-002", "인증이 필요합니다"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "ACCOUNT-003", "권한이 필요합니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "ACCOUNT-004", "비밀번호가 올바르지 않습니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-005", "사용자를 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-001", "서버에 에러가 발생했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}