package egservis.services.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.constraints.NotNull;
import egservis.persistence.entities.Cliente;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.DatosListadoCliente;

public interface ClienteService {

    ClienteDTO findByDni(String dni);

    void save(@NotNull Cliente cliente);

    Optional<Cliente> deleteLogic(Long id);

    Optional<ClienteDTO> findById(@NotNull Long id);

    void deleteById(Long id);

    boolean existsByDni(String dni);

    boolean existsById(@NotNull Long id);

    Page<DatosListadoCliente> getAll(Pageable pageable);

    Optional<Cliente> update(@NotNull Long id, @NotNull ClienteUpdateDTO clienteUpdateDTO);
}
