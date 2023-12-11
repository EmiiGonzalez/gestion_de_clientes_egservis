package egservis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import egservis.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByDni(String dni);

    public Boolean existsByDni(String dni);
}
