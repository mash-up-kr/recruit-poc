package kr.mashup.branding.recruitpoc.infrastructure.spring.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/web").permitAll()
            .antMatchers("/web/login").permitAll()
            .antMatchers("/web/teams").permitAll()
            .anyRequest().hasAuthority("ROLE_USER")
        http.oauth2Login()
    }
}