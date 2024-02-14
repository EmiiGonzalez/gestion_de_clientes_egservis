package egservis.services.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.constraints.NotNull;
import egservis.persistence.entities.Cliente;
import egservis.persistence.entities.Pedido;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.DatosListadoClienteDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;

public interface ClienteService {

    ClienteDTO save(ClienteDTO cliente) throws ClienteExistenteException;

    String deleteLogic(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException;

    Page<DatosListadoClienteDTO> getAllActive(Pageable pageable);

    Page<DatosListadoClienteDTO> getAll(Pageable pageable);

    ClienteDTO findByDni(String dni) throws ClienteNoExistenteException;

    ClienteDTO getById(@NotNull Long id) throws ClienteNoExistenteException;

    Optional<Cliente> update(@NotNull Long id, @NotNull ClienteUpdateDTO clienteUpdateDTO) throws ClienteNoExistenteException;

    Optional<Cliente> activate(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException;

    void addPedido(Pedido pedido, Long id) throws ClienteNoExistenteException;
}
