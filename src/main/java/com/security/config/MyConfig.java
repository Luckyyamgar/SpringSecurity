package com.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyConfig {

	@Bean
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				
				.requestMatchers("/users/alluser/**").permitAll()
				
				//.authenticated()                                                 // .hasRole("EMPLOYEE")
                .anyRequest()
                .authenticated())
                //.permitAll())                                                   // .permitAll() PathRequest.toAntMatchers
				.formLogin(Customizer.withDefaults());

		return http.build();	
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails normaluser = User.withUsername("mhaku").password(passwordEncoder().encode("Shravan"))
				.roles("EMPLOYEE").build();

		UserDetails adminUser = User.withUsername("onkar").password(passwordEncoder().encode("onkar")).roles("MANAGER")
				.build();
		return new InMemoryUserDetailsManager(normaluser, adminUser);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
