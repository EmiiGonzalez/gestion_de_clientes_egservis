package egservis.services;

import java.util.List;
import org.springframework.stereotype.Service;

import egservis.models.Cliente;
import egservis.repository.ClienteRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void update(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByDni(String dni) {
        return clienteRepository.existsByDni(dni);
    }

    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }
    

}
