package com.jinhe.common.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private Property property;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
        /** 地址过滤 */
        String uri = request.getRequestURI();

        if (property.getSpringProfilesActive().equals(SystemType.prod)) {
            if (property.getFilters().stream().filter(d -> uri.contains(d)).count() == 0
            ) {
                return true;
            }
        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            throw new SignatureException(jwtConfig.getHeader() + "不能为空");
        }
        if (token == null) {
            return false;
        }
        if (token.startsWith(jwtConfig.getTokenStartWith())) {
            token = token.replace(jwtConfig.getTokenStartWith(), "").trim();
        }

        Claims claims = null;
        try {
            claims = jwtConfig.getTokenClaim(token);
            if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        } catch (Exception e) {
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }

        /** 设置 identityId 用户身份ID */
        request.setAttribute(SystemType.identityId, claims.getSubject());
        request.setAttribute(SystemType.USER_ID, claims.get(SystemType.USER_ID));
        request.setAttribute(SystemType.USER_NAME, claims.get(SystemType.USER_NAME));
        return true;
    }


}