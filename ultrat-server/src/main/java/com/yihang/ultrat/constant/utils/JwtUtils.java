package com.yihang.ultrat.constant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class JwtUtils {
    @Value("${ultrat.jwt.secret}")
    private String secret;

    private static final String UID_CLAIM = "uid";

    private static final String CREATE_TIME = "createTime";

    public String createToken(Long uid) {
        String token = JWT.create()
                .withClaim(UID_CLAIM, uid)
                .withClaim(CREATE_TIME, new Date())
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    public Map<String, Claim> verifyToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            log.error("decode failed, token: {}", token, e);
        }
        return null;
    }

    public Long getUidOrNull(String token) {
        return Optional.ofNullable(verifyToken(token))
                .map(map -> map.get(UID_CLAIM))
                .map(Claim::asLong)
                .orElse(null);
    }
}
