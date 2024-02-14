package egservis.persistence.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import egservis.persistence.entities.Cliente;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.DatosListadoClienteDTO;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteDTO(c) FROM Cliente c WHERE c.dni=?1")
    public Optional<ClienteDTO> findByDni(String dni);

    public Boolean existsByDni(String dni);

    @Query("SELECT new egservis.services.models.dto.cliente.DatosListadoClienteDTO(c) FROM Cliente c WHERE c.activo=true")
    public Page<DatosListadoClienteDTO> findAllByActivoTrue(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.cliente.DatosListadoClienteDTO(c) FROM Cliente c")
    public Page<DatosListadoClienteDTO> findAllPage(Pageable pageable);

    @Query("SELECT new egservis.services.models.dto.cliente.ClienteDTO(c) FROM Cliente c WHERE c.id=?1")
    public Optional<ClienteDTO> findByIdDTO(Long id);

}
