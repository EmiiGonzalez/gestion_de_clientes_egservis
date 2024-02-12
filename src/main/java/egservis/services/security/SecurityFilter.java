package egservis.services.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import egservis.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component // Estereotipo mas comun de Spring
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Entrando al filtro");

        // Obteniendo el token

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null ) {
            String token = authHeader.substring(7);
            
            String nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                //token valido
                //busco el usuario por db
                UserDetails usuario = usuarioRepository.findByLogin(nombreUsuario);
                //genero el token del usuario para authenticar forzando el inicio de session
                Authentication  authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                //authentico el usuario y en el contexholder lo seteo manualmente
                SecurityContextHolder.getContext().setAuthentication(authentication);;
            }
            
        }

        filterChain.doFilter(request, response);

    }
}
