package egservis.services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//Para que spring sepa que esta es una configuracion de seguridad se usa @EnableWebSecurity
@EnableWebSecurity  
public class SecurityConfiguration {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())       //como es via token no hay csrf
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //session no es persistente en el navegador
            .build();

    }
}
