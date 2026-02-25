package com.roc.apiclientsdk.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.NonNull;

/**
 * @author lipeng
 * @since 2026/2/25 16:15
 */
public class SignUtil {

    /**
     * @param accessKey       用户身份凭证
     * @param nonce           随机数，防重放
     * @param timestamp       时间戳，和随机数配合使用，防重放
     * @param secretKey       用户身份密钥
     * @param bodyJson        请求体JSON字符串
     * @return 对于上述防篡改部分的SHA-512哈希值
     */
    public static byte[] genSignBySha512(String accessKey,
                                         String nonce,
                                         String timestamp,
                                         String secretKey,
                                         String bodyJson) {
        String data = accessKey + nonce + timestamp + secretKey + bodyJson;
        return DigestUtil.digester(DigestAlgorithm.SHA512).digest(data);
    }
}
