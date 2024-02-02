package egservis.services.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.constraints.NotNull;

import egservis.Dto.Cliente.DatosListadoCliente;
import egservis.models.Cliente;

public interface ClienteService {

    Cliente findByDni(String dni);

    void save(@NotNull Cliente cliente);

    void delete(Cliente cliente);

    Cliente findById(@NotNull Long id);

    void deleteById(Long id);

    boolean existsByDni(String dni);

    boolean existsById(@NotNull Long id);

    Page<DatosListadoCliente> getAll(Pageable pageable);
}