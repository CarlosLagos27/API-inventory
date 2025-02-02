        package com.inventory.configurations;

        import com.inventory.filters.JwtRequestFilter;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

        @Configuration
        @EnableWebSecurity
        public class SecurityConfig {

            @Autowired
            private JwtRequestFilter jwtRequestFilter;

            @Bean
            public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/api/auth/**",
                                        "/api/users/register"
                                ).permitAll()
                                 // Allow access to authentication endpoints
                                .requestMatchers("/api/tickets/myTickets").hasAuthority("USER")
                                .requestMatchers("/api/tickets").hasAnyAuthority("USER", "ADMIN")  // Allow USER and ADMIN to access tickets
                                .requestMatchers("/api/tickets/**").hasAuthority("ADMIN")  // Only ADMIN access
                                .anyRequest().authenticated()  // All other endpoints require authentication
                        )
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session
                        )
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter

                return http.build();
            }

            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }

            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
            }
        }