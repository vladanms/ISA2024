package com.example.ISA2024_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{
	
 /*   @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("users/login")
                        .defaultSuccessUrl("/secured-endpoint", true)
                        .permitAll());

        return http.build();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF and allow all requests without authentication
        http.csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll();  // Permit all requests without authentication
        return http.build(); // Finalize the security configuration
    } 
    
    
//    protected void configure(HttpSecurity http) throws Exception {
//
//       http.csrf(csrf -> csrf.disable())
//               .authorizeRequests(requests -> requests
//                       .anyRequest().permitAll());
//	   
//        /* auth.inMemoryAuthentication()
//             .passwordEncoder(passwordEncoder())
//             .withUser(User.builder()
//                     .username("user")
//                     .password(passwordEncoder().encode("password"))  // Encode password
//                     .roles("USER")
//                     .build());*/
//       
//	 /*  http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(requests -> requests
//                        .antMatchers("/login", "/register").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .permitAll());
//                  */      
//    } 
    
    //test
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password(passwordEncoder().encode("password"))  // Encode password
                .roles("USER")
                .build()
        );

}
};