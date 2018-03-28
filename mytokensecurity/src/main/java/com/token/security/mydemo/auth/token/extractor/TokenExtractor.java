package com.token.security.mydemo.auth.token.extractor;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/12.
 * @description
 */
public interface TokenExtractor {
    String extract(String payload);
}
