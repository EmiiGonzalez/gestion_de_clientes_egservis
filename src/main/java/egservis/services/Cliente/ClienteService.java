package egservis.services.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.constraints.NotNull;
import egservis.persistence.entities.Cliente;
import egservis.persistence.entities.Dispositivo;
import egservis.services.models.dto.cliente.ClienteCountResponseDTO;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.ClienteResponseDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;

public interface ClienteService {

    ClienteDTO save(ClienteDTO cliente) throws ClienteExistenteException;

    String deleteLogic(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException;

    Page<ClienteResponseDTO> getAllActive(Pageable pageable);

    Page<ClienteResponseDTO> getAll(Pageable pageable);

    ClienteResponseDTO findByDni(String dni) throws ClienteNoExistenteException;

    ClienteResponseDTO getById(@NotNull Long id) throws ClienteNoExistenteException;

    ClienteDTO update(@NotNull Long id, @NotNull ClienteUpdateDTO clienteUpdateDTO) throws ClienteNoExistenteException;

    Optional<Cliente> activate(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException;

    void addDispositivo(Dispositivo dispositivo, Long id) throws ClienteNoExistenteException;

    ClienteCountResponseDTO findAllByMes(Integer year);
}
