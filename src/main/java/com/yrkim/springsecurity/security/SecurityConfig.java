package com.yrkim.springsecurity.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/*
 * EnableWebSecurity : 스프링시큐리티 활성화
 * 내부에 @Configuration 포함
 * EnableGlobalMethodSecurity(prePostEnabled = true) :
 *   Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우
 *   @PreAuthorize 어노테이션을 사용하는데,
 *   해당 어노테이션에 대한 설정을 활성화 (필수x)
 * */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private static final String[] exposeHeaders = {
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "Access-Control-Allow-Methods",
            "Access-Control-Max-Age",
            "Access-Control-Allow-Headers",
            "Origin",
            "X-Requested-With",
            "Content-Type",
            "Accept",
            "Key",
            "Authorization",
            "Vary",
            "Access-Control-Expose-Headers",
            "Access-Control-Request-Headers"};

    static String[] allowedOrigin = {
            "https://localhost:3000",
            "https://localhost:9000",
            "http://rokidev.com",
            "https://rokidev.com"
    };

    private static final String[] CONF_POST_PATHS = {
            ""
    };

    private static final String[] CONF_GET_PATHS = {
            ""
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /*httpSecurity
                // cors 설정
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                    .antMatchers(CONF_POST_PATHS).permitAll()
                    .antMatchers(CONF_GET_PATHS).permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable().headers().referrerPolicy();*/

        //httpSecurity.addFilterBefore();

        // 기본 성공 / 실패 시 url 연결 등등
       /*httpSecurity
                .formLogin()
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_conf")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        log.info("onAuth :",String.format("%s",authentication.getName()));
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        log.info("onfail : ",String.format("%s",exception.getMessage()));
                        response.sendRedirect("/login");
                    }
                })*/
        // remeber 체크시 session 저장
        /*.and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })
                .deleteCookies("remember-me")*/
        /*.and()
                // sessionManagement 세션 관리 기능 작동
                .sessionManagement()
                // maximumSessions 로그인 세션 최대 1개
                .maximumSessions(1)
                // 다른 곳에서 로그인시 세션이 새로 발급된다면 기존 세션 아이디로 로그인은 불가능
                .maxSessionsPreventsLogin(false)*/
        /*.and()
                // sessionManagement 세션 관리 기능 작동
                .sessionManagement()
                // sessionFixation 기존 세션 그대로 이용
                .sessionFixation()
                // changeSessionId session아이디만 변경
                .changeSessionId()*/
        /*
        * security의 인증 api 종류로는
        * 1. 세션 관리 : 인증시 사용자의 세션 정보와 이력을 관리
        * 2. 동시적 세션 제어 : 동일한 계정으로 접속이 허용되는 세션 수를 제한
        * 3. 세션 고정 보호 : 인증 할 때 마다 세션쿠키를 새로 발급하여 공격 방지
        * 4. 세션 생성 정책 : Always , If_Required , Never , Stateless
        * */
    }

    // WebSecurity : 보안 예외 처리
    // Security filter chain 적용할 필요가 전혀 없는 요청을 ignore하고 싶을 때 사용
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(
                "/swagger/**",
                "/api/**",
                "/swagger-ui.html",
                "/swagger-ui/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList(allowedOrigin));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setExposedHeaders(Arrays.asList(exposeHeaders));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
