package com.roc.apiclientsdk.client;

import java.util.HashMap;
import java.util.Map;

import com.roc.apiclientsdk.module.User;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lipeng
 * @since 2026/2/24 17:38
 */
@AllArgsConstructor
@Slf4j
public class ApiClient {
    private String accessKey;
    private String secretKey;

    /**
     * todo 根据url来调用接口
     */
    public String getName(User user) {
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8010/api/name")
                .addHeaders(getHeaderMap())
                .body(JSONUtil.toJsonStr(user))
                .execute();
        String result = httpResponse.body();
        log.info("ApiClient#getName result status :{}", httpResponse.getStatus());
        log.info("ApiClient#getName result:{}", result);
        return result;
    }

    private Map<String, String> getHeaderMap() {
        HashMap<String, String> headerMap = new HashMap<>(5);
        headerMap.put("Content-Type", "application/json");
        // ak nonce timestamp sign->sha512(ak+nonce+timestamp+sk)
        return headerMap;
    }

}
