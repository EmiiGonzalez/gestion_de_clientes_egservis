package egservis.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egservis.Entities.Usuario;
import egservis.services.models.dto.login.LoginDTO;
import egservis.services.models.dto.login.TokenDTO;
import egservis.services.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.clave());

        var usuarioAutenticado = authenticationManager.authenticate(token);  //con esto se valida el token con la base de datos

        String tokenJWT = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok().body(new TokenDTO(tokenJWT));
    }
}
