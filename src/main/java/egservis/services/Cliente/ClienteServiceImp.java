package egservis.services.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egservis.Entities.Cliente;
import egservis.repository.ClienteRepository;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.DatosListadoCliente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImp implements ClienteService{
    
    private final ClienteRepository clienteRepository;

    public ClienteDTO findByDni(String dni) {
        return clienteRepository.findByDni(dni).orElse(null);
    }

    public void save(@NotNull Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> deleteLogic(Long id) {

        Optional<Cliente> c = clienteRepository.findById(id);

        c.ifPresent(c1 -> {
            c1.desactivar();
        });

        return c;
        
    }

    public Optional<ClienteDTO> findById(@NotNull Long id) {
        return clienteRepository.findByIdDTO(id);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByDni(String dni) {
        return clienteRepository.existsByDni(dni);
    }

    public boolean existsById(@NotNull Long id) {
        return clienteRepository.existsById(id);
    }

    public Page<DatosListadoCliente> getAll(Pageable pageable) {
        //return clienteRepository.findAll(pageable).map(DatosListadoCliente::new);
        return clienteRepository.findByActivoTrue(pageable).map(DatosListadoCliente::new);
    }

    @Override
    @Transactional
    public Optional<Cliente> update(Long id, ClienteUpdateDTO clienteUpdateDTO) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        cliente.ifPresent(c -> c.actualizarDatos(clienteUpdateDTO));

        return cliente;
    }
}
