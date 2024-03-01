package egservis.services.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.persistence.entities.Cliente;
import egservis.persistence.entities.Dispositivo;
import egservis.persistence.repository.ClienteRepository;
import egservis.services.models.dto.cliente.ClienteCountResponseDTO;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.ClienteResponseDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO findByDni(String dni) throws ClienteNoExistenteException {
        Optional<ClienteResponseDTO> c = clienteRepository.findByDni(dni);
        if (!c.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el dni " + dni + " no existe");
        }
        return c.get();
    }

    @Override
    public ClienteResponseDTO getById(@NotNull Long id) throws ClienteNoExistenteException {
        Optional<ClienteResponseDTO> c = clienteRepository.findByIdDTO(id);
        if (!c.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el id " + id + " no existe");
        }
        return c.get();
    }

    @Override
    public ClienteDTO save(@NotNull ClienteDTO cliente) throws ClienteExistenteException {
        if (clienteRepository.existsByDni(cliente.dni())) {
            throw new ClienteExistenteException("Ya existe un cliente con el DNI " + cliente.dni());
        }
        Cliente c = new Cliente(cliente);
        return new ClienteDTO(clienteRepository.save(c));
    }

    @Override
    public Page<ClienteResponseDTO> getAllActive(Pageable pageable) {
        return clienteRepository.findAllByActivoTrue(pageable);
    }

    @Override
    public Page<ClienteResponseDTO> getAll(Pageable pageable) {
        return clienteRepository.findAllPage(pageable);
    }

    @Override
    public String deleteLogic(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException {

        Optional<Cliente> c = clienteRepository.findById(id);

        if (!c.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el id " + id + " no existe");
        }

        if (!c.get().getActivo()) {
            throw new ClienteDesactivadoException("El cliente con el id " + id + " ya se encuentra desactivado");
        }

        c.get().desactivar();

        return c.get().getDni();
    }

    @Override
    public ClienteDTO update(Long id, ClienteUpdateDTO clienteUpdateDTO) throws ClienteNoExistenteException {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el id " + id + " no existe");
        }

        cliente.get().actualizarDatos(clienteUpdateDTO);

        return new ClienteDTO(cliente.get());
    }

    @Override
    public Optional<Cliente> activate(Long id) throws ClienteNoExistenteException, ClienteDesactivadoException {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el id " + id + " no existe");
        }

        if (!cliente.get().getActivo()) {
            throw new ClienteDesactivadoException("El cliente con el id " + id + " ya se encuentra activado");
        }

        cliente.get().activar();

        return cliente;

    }

    @Override
    public void addDispositivo(Dispositivo dispositivo, Long id) throws ClienteNoExistenteException {

        Optional<Cliente> c = clienteRepository.findById(id);

        if (!c.isPresent()) {
            throw new ClienteNoExistenteException("El cliente con el id " + id + " no existe");
        }

        c.get().addDispositivo(dispositivo);
    }

    @Override
    public ClienteCountResponseDTO findAllByMes(Integer year) {
        return new ClienteCountResponseDTO(clienteRepository.findAllByMes(year));
    }

}
