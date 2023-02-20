package com.management.restfulwebservices.basic.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigBasicAuth {
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		
		UserDetails user = User.withUsername("my_user")
				.password(encoder.encode("pass"))
				.roles("user")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable()
//			.authorizeHttpRequests()
//			.requestMatchers("/**").authenticated()
//			.and().formLogin()
//			.and().build();
//	}
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeHttpRequests()
//			.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.httpBasic();
//		http.cors();
//		return http.build();
//		}
    @SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        http.csrf().disable();
        http.cors();
        return http.build();
    }	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
