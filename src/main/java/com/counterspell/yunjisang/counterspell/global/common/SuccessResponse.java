package com.counterspell.yunjisang.counterspell.global.common;

import lombok.Data;

@Data
public class SuccessResponse<T> {
    private boolean success;
    private T data;

    public SuccessResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
}
