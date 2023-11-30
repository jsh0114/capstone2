package com.capstone.teamProj_10.apiTest.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/user/pwchange")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/deleteAll")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("api/products/request")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/productRequests/{id}")).permitAll()
			.and()
			.csrf()
			.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
			.ignoringRequestMatchers(new AntPathRequestMatcher("/user/pwchange"))
			.ignoringRequestMatchers(new AntPathRequestMatcher("/deleteAll"))
			.ignoringRequestMatchers(new AntPathRequestMatcher("/api/products/request"))
			.ignoringRequestMatchers(new AntPathRequestMatcher("/api/productRequests/{id}"))
			.and()
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
			.and()
			.formLogin()
			.loginPage("/user/login")
			.defaultSuccessUrl("/")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
