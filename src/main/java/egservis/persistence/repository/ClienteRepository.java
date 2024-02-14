package egservis.persistence.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import egservis.persistence.entities.Cliente;
import egservis.services.models.dto.cliente.ClienteResponseDTO;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteResponseDTO(c) FROM Cliente c WHERE c.dni=?1")
    public Optional<ClienteResponseDTO> findByDni(String dni);

    public Boolean existsByDni(String dni);

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteResponseDTO(c) FROM Cliente c WHERE c.activo=true")
    public Page<ClienteResponseDTO> findAllByActivoTrue(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteResponseDTO(c) FROM Cliente c")
    public Page<ClienteResponseDTO> findAllPage(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteResponseDTO(c) FROM Cliente c WHERE c.id=?1")
    public Optional<ClienteResponseDTO> findByIdDTO(Long id);

}
