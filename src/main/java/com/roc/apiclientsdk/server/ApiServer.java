package com.roc.apiclientsdk.server;

import java.util.Arrays;

import com.roc.apiclientsdk.module.response.ApiResponse;
import com.roc.apiclientsdk.util.SignUtil;

import lombok.AllArgsConstructor;

/**
 * 小项目，API提供方验签逻辑和调用方签名逻辑放在同一个项目中
 * @see com.roc.apiclientsdk.client.ApiClient
 * @author lipeng
 * @since 2026/2/25 15:19
 */
@AllArgsConstructor
public class ApiServer {
    private String accessKey;
    private String secretKey;

    /**
     * @see com.roc.apiclientsdk.util.SignUtil#genSignBySha512(String, String, String, String, String)
     * @param signature API签名
     * @return API提供方验签结果
     */
    public ApiResponse verifySignature(String signature, String nonce, String timestamp, String bodyJson) {
        byte[] genned = SignUtil.genSignBySha512(accessKey, nonce, timestamp, secretKey, bodyJson);
        if (genned == null || genned.length == 0) {
            return ApiResponse.fail("无法生成签名");
        }
        if (!Arrays.toString(genned).equals(signature)) {
            return ApiResponse.fail("验签失败");
        }
        return ApiResponse.success(null);
    }

}
