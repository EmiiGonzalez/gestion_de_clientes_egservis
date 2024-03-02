package egservis.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import egservis.persistence.entities.Usuario;
import egservis.services.models.dto.login.LoginDTO;
import egservis.services.models.dto.login.RegisterDTO;
import egservis.services.models.dto.login.TokenDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.security.TokenService;
import egservis.services.usuario.AuthenticacionServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private AuthenticacionServiceImp authenticationService;
    
    @PostMapping(value = "/login", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.clave());

        var usuarioAutenticado = authenticationManager.authenticate(token);  //con esto se valida el token con la base de datos

        String tokenJWT = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok().body(new TokenDTO(tokenJWT));
    }

    @PostMapping(value = "/register", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) throws ClienteExistenteException {
        Usuario usuario = authenticationService.register(registerDTO);
        String tokenJWT = tokenService.generateToken(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDTO(tokenJWT));
    }
}
