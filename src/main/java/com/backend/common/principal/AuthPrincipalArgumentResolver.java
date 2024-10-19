package com.backend.common.principal;

import com.backend.auth.domain.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;
    private static final String TOKEN_PREFIX = "Bearer ";

    public AuthPrincipalArgumentResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try{
            String authToken = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (!StringUtils.hasText(authToken) || authToken.split(" ").length != 2){
                throw new IllegalArgumentException("토큰이 존재하지 않거나 올바르지 않은 토큰입니다.");
            }

            String token = authToken.substring(TOKEN_PREFIX.length());
            Long userId = tokenProvider.getUserId(token);
            String userRole = tokenProvider.getUserRole(token);

            return new UserPrincipal(userId, userRole);
        }catch (Exception e){
            throw new IllegalArgumentException("올바르지 않은 토큰 값입다.", e);
        }
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }
}
