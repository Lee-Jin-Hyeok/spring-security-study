package com.dsm.springsecuritystudy.configuration

import com.dsm.springsecuritystudy.controller.filter.AuthenticationFilter
import com.dsm.springsecuritystudy.service.provider.TokenProvider
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val tokenProvider: TokenProvider,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/join", "/**/login").permitAll()
            .antMatchers(HttpMethod.GET, "/hello/**").permitAll()
            .anyRequest().hasRole("USER")
            .and()
            .addFilterBefore(AuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger/**", "/v2/api-docs", "/swagger-resources/**")
    }
}