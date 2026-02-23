package tech.buildrun.livechatms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         return http
            .csrf(customizer -> customizer.disable()) // Disable CSRF protection for simplicity (not recommended for production)
            .authorizeHttpRequests(request -> request
                .requestMatchers("/register", "/login", "/css/**", "/js/**","/bootstrap/**","/img/**","/brand/**")// Allows access to the specified endpoints without authentication  
                .permitAll() // Allows access to the specified endpoints without authentication
                .anyRequest().authenticated()) // Requires authentication for any other request
            .formLogin(form -> form 
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/chat",true)
                .permitAll()) // Configure form-based login with a custom login page and success/failure URLs
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            )
            .build();
    }

    // Password encoder bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);       // Compare hashed passwords
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }


}
