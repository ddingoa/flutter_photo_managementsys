package com.ddingo.photoservice.util.jwtUtil;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "ddingoToken";
    public static final String BEARER_PREFIX = "DDingo ";

    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("HttpServletRequest ------------------ request" + request);
        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = resolveToken(request);
        logger.debug("jwt------------------Filter");
        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        logger.debug("jwt------------------ |||||  " + jwt);
        logger.debug("jwt------------------ |||||  " + StringUtils.hasText(jwt));
        logger.debug("jwt------------------ |||||  " + tokenProvider.validateToken(jwt));
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            logger.debug("jwt Authentication DoFilter ------------------" + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        logger.debug("jwt End DoFilter ------------------");
        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        logger.debug("jwt------------------resolveToken");
        logger.debug("jwt------------------AUTHORIZATION_HEADER " +  request.getHeader(AUTHORIZATION_HEADER)==null ? null :  request.getHeader(AUTHORIZATION_HEADER));
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        logger.debug("jwt------------------bearerToken" + bearerToken == null ? null :bearerToken );
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            logger.debug("jwt------------------resolveToken" + bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
