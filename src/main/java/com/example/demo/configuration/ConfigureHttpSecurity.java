package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ConfigureHttpSecurity {

		@Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}

		@Bean
			public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				http
							.authorizeHttpRequests((authorizeHttpRequests) ->
								authorizeHttpRequests
								// only authenticated user with role ADMIN can access path /employees and other paths
								.requestMatchers("/employees","/employee/edit/*","/*/*","/employee").hasRole("ADMIN")
							)
						.formLogin(withDefaults())
						.logout(o -> o.logoutRequestMatcher((new AntPathRequestMatcher(("/logout")))).logoutSuccessUrl("/employees"));
			return http.build();
			}

		@Bean
		@Description("In memory Userdetails service registered since DB doesn't have user table ")
		public UserDetailsService users() {
			// The builder will ensure the passwords are encoded before saving in memory
			UserDetails user = User.builder()
					.username("user")
					//Adding password storage format
					.password(passwordEncoder().encode("password"))
					.roles("USER")
					.build();
			UserDetails admin = User.builder()
					.username("admin")
					.password(passwordEncoder().encode("password"))
					.roles("ADMIN")
					.build();
			return new InMemoryUserDetailsManager(user, admin);
		}
}
         
