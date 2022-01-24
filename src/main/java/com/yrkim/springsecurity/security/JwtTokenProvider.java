package com.yrkim.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* JwtTokenProvider
* : jwt 토큰 생성과 검증 작업
* */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements Serializable {
    private static final long serialVersionUID = 8161443083842108702L;

    public static final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 14;  // 7일
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 10 * 60 * 60; // 10분

    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";

    private final UserDetailsServiceImpl userDetailsServiceImpl;


}
