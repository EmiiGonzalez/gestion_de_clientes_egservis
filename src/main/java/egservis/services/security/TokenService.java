package egservis.services.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import egservis.Entities.Usuario;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(Usuario user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("egservis")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-3"));
    }
}
