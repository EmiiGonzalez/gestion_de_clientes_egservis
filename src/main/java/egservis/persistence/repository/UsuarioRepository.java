package egservis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import egservis.persistence.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Boolean existsByLogin(String login);

    public UserDetails findByLogin(String login);

}
