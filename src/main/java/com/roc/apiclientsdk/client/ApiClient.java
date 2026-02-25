package com.roc.apiclientsdk.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.roc.apiclientsdk.module.User;
import com.roc.apiclientsdk.util.SignUtil;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;

/**
 * @author lipeng
 * @since 2026/2/24 17:38
 */
@AllArgsConstructor
public class ApiClient {
    private String accessKey;
    private String secretKey;

    /**
     * todo 根据url来调用接口
     */
    public String getName(User user) {
        String bodyJson = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8010/api/name")
                .addHeaders(getHeaderMap(bodyJson))
                .body(bodyJson)
                .execute();
        String result = httpResponse.body();
        System.out.println("ApiClient#getName result status : " +  httpResponse.getStatus());
        System.out.println("ApiClient#getName result: " + result);
        return result;
    }

    /**
     *
     * @param bodyJson 请求体JSON字符串
     * @return 请求头Map
     */
    private Map<String, String> getHeaderMap(String bodyJson) {
        HashMap<String, String> headerMap = new HashMap<>(5);
        headerMap.put("Content-Type", "application/json");
        // ak nonce timestamp sign->sha512(ak+nonce+timestamp+sk)
        headerMap.put("access-key", accessKey);
        String nonce = RandomUtil.randomNumbers(5);
        headerMap.put("nonce", nonce);
        String timestamp = String.valueOf(System.currentTimeMillis());
        headerMap.put("timestamp", timestamp);
        byte[] gennedSign = SignUtil.genSignBySha512(accessKey, nonce, timestamp, secretKey, bodyJson);
        String sign = Arrays.toString(gennedSign);
        headerMap.put("sign", sign);
        return headerMap;
    }

}
