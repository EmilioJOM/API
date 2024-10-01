package com.uade.tpo.backendGrupo6MieMa.controllers.config;

import com.uade.tpo.backendGrupo6MieMa.controllers.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(req -> req
                                .requestMatchers("/api/v1/auth/register").permitAll()
                                .requestMatchers("/api/v1/auth/authenticate").permitAll()
//                                .requestMatchers("/api/v1/auth/confirm-registration").permitAll()
//                                .requestMatchers("/Categoria/**").hasRole("USER") // Usa ADMIN directamente
//                                .requestMatchers("/Marca/**").permitAll() // Usa ADMIN directamente
//                                .requestMatchers("/Detalle/**").hasAnyRole("USER", "ADMIN") // Usa USER y ADMIN directamente
//                                .requestMatchers(HttpMethod.GET, "/Orden").hasRole("ADMIN") // Usa USER directamente
//                                .requestMatchers(HttpMethod.GET, "/Producto/**").hasRole("USER") // Usa USER directamente
//                                .requestMatchers(HttpMethod.POST, "/Producto").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.PUT, "/Producto/{id}/stock").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.PUT, "/Producto/{id}/descuento").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.PUT, "/Producto/{id}/precio").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.DELETE, "/Producto").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.POST, "/Orden/{ordenId}/confirmar").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.POST, "/Orden/{ordenId}/agregarDetalle").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.DELETE, "/Orden").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers("/Usuario/**").hasRole("ADMIN") // Usa ADMIN directamente
//                                .requestMatchers(HttpMethod.GET, "/Usuario/{id}").hasRole("USER") // Usa USER directamente
                                .anyRequest().authenticated())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(daoAuthenticationProvider())
                        .cors(withDefaults())
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public AuthenticationProvider daoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                provider.setPasswordEncoder(passwordEncoder);
                return provider;
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
                return new WebMvcConfigurer() {
                        @Override
                        public void addCorsMappings(CorsRegistry registry) {
                                registry.addMapping("/**")
                                        .allowedOrigins("http://localhost:4002")
                                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                        .allowedHeaders("*")
                                        .allowCredentials(true);
                        }
                };
        }
}
