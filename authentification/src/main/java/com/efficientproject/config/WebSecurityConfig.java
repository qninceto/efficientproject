package com.efficientproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)// ?
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
	auth.inMemoryAuthentication()
	  .withUser("qna").password("12345q").roles("ADMIN").and()
	  .withUser("user1").password("pass").roles("USER");
    }// @formatter:on
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/login", "/oauth/token/revokeById/**", "/tokens/**").permitAll()
			.antMatchers("/h2/**").permitAll()
		.anyRequest().authenticated()
			.and()
		.formLogin().permitAll()
			.and()
		.csrf().disable();
		http.headers().frameOptions().disable();//h2
    }
}
