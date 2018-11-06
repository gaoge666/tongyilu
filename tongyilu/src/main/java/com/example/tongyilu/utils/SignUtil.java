package com.example.tongyilu.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 加验签工具
 *
 * @author xwolf
 **/
@Slf4j
public class SignUtil {

    /**
     * 加签
     *
     * @param map
     * @return
     */
    public static String sign(Map<String, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        TreeMap<String, Object> params = new TreeMap<>(map);
        StringBuilder res = new StringBuilder();
        params.entrySet().forEach(kv -> {
            if (!Objects.equals("timestamp", kv.getKey()) ||
                    !Objects.equals("sign", kv.getKey())) {
                res.append(kv.getKey()).append("=").append(kv.getValue()).append("&");
            }
        });
        String signStr = res.substring(0, res.length() - 1);
        return DigestUtils.md5Hex(signStr);
    }
}
