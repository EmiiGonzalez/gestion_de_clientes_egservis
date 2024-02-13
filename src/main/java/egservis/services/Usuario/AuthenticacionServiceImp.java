package egservis.services.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import egservis.persistence.entities.Usuario;
import egservis.persistence.repository.UsuarioRepository;
import egservis.services.models.dto.login.RegisterDTO;
import egservis.services.models.exceptions.UsuarioExistenteException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticacionServiceImp implements  UserDetailsService {

    private UsuarioRepository repository;
    private PasswordEncoder passwordEncoder;

    @Override
    //De donde cargo los datos y como cargo
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public Usuario register(RegisterDTO registerDTO) throws UsuarioExistenteException {
        if (repository.existsByLogin(registerDTO.login())) {
            throw new UsuarioExistenteException(
                String.format("El usuario ya existe", registerDTO.login()));
        };

        Usuario usuario = new Usuario();
        usuario.setLogin(registerDTO.login());
        usuario.setClave(passwordEncoder.encode(registerDTO.clave()));

        return repository.save(usuario);
    }
    
}