package com.token.security.mydemo.auth.token.verifier;

/**
 * Token验证
 * 
 * @author Levin
 *
 * @time 2017-05-25
 */
public interface TokenVerifier {
    boolean verify(String jti);
}
