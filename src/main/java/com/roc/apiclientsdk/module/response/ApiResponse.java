package com.roc.apiclientsdk.module.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lipeng
 * @since 2026/2/25 15:23
 */
@AllArgsConstructor
@Data
public class ApiResponse {
    @Schema(description = "0 - 调用成功，非0 - 调用失败")
    private int code;

    private String message;

    private Object data;

    public static ApiResponse fail(String message) {
        return new ApiResponse(-1, message, null);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(0, "OK", data);
    }
}
