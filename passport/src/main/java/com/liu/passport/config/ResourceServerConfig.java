package com.liu.passport.config;

import com.liu.passport.handler.AjaxLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String RESOURCE_ID = "account";

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    //需要resource id为"account"的oauth client才能访问
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(true);
//        super.configure(resources);
    }

    // /oauth/info 的resource server授权服务
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling().authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
//                .and()
//                .logout().logoutUrl("/user/logout")
//                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
//                .and()
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**")
//                .permitAll().antMatchers("/api/account")
//                .authenticated().antMatchers("/oauth/info").authenticated();

        http.exceptionHandling().authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .and().logout().logoutUrl("/api/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .and().csrf().disable().headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/api/email").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/account").authenticated();
//                .antMatchers("/oauth/info").authenticated();


        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .requestMatchers().antMatchers("/api/**")
                .and()
                .logout().logoutUrl("/api/exit").logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user").authenticated()
//                .antMatchers("/oauth/info").authenticated()
                .and()
                .httpBasic();
    }
}
