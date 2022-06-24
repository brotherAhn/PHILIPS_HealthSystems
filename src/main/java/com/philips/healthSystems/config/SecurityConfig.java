package com.philips.healthSystems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	 @Override
	    public void configure(HttpSecurity http) throws Exception {
		 	http.csrf().disable()
		 	.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers(
                    "/json/**")
            .authenticated()
            // all other requests need to be authenticated
            .anyRequest().permitAll();
		 	http.httpBasic().disable();
	        http.headers().frameOptions().sameOrigin();
	        http.headers().xssProtection();
	        http.headers().httpStrictTransportSecurity();
	        http.headers().contentTypeOptions();
	        http.headers().contentSecurityPolicy("frame-ancestors 'self' https://healthsystems.tckdigital.com/*");
	        http.headers().addHeaderWriter(new StaticHeadersWriter("Server","HealthSystems"));
	 }
	 

	    @Bean
	    @Override
	    public UserDetailsService userDetailsServiceBean() throws Exception {
	        return super.userDetailsServiceBean();
	    }

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}
