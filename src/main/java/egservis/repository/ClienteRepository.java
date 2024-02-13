package egservis.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import egservis.Entities.Cliente;
import egservis.services.models.dto.cliente.ClienteDTO;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteDTO(c) FROM Cliente c WHERE c.dni=?1")
    public Optional<ClienteDTO> findByDni(String dni);

    public Boolean existsByDni(String dni);

    public Page<Cliente> findByActivoTrue(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteDTO(c) FROM Cliente c WHERE c.id=?1")
    public Optional<ClienteDTO> findByIdDTO(Long id);

}
