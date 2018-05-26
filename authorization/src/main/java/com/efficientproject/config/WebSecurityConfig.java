package com.efficientproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)// ?
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    @SuppressWarnings("deprecation")
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
	auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())/*passwordEncoder(passwordEncoder())  */
	  .withUser("admin").password("admin").roles("ADMIN").and()
	  .withUser("user").password("user").roles("USER");
    }// @formatter:on
	
//    UserBuilder users = User.withDefaultPasswordEncoder();
//    User user = users
//      .username("user")
//      .password("password")
//      .roles("USER")
//      .build();
//    User admin = users
//      .username("admin")
//      .password("password")
//      .roles("USER","ADMIN")
//      .build();
    
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
			.antMatchers("/h2/**").hasRole("ADMIN")
		.anyRequest().authenticated()
			.and()
		.formLogin().permitAll()
			.and()
		.csrf().disable();
		http.exceptionHandling().accessDeniedPage("/403");
		
		/*H2 database console runs inside a frame, So we need to disable X-Frame-Options in Spring Security.*/		
		http.headers().frameOptions().disable();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
