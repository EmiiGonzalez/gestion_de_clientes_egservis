package egservis.services.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.Dto.Cliente.DatosListadoCliente;
import egservis.models.Cliente;
import egservis.repository.ClienteRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteServiceImp implements ClienteService{
    
    private final ClienteRepository clienteRepository;

    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public void save(@NotNull Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Cliente findById(@NotNull Long id) {
        return clienteRepository.findById(id).orElse(null);
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
}
