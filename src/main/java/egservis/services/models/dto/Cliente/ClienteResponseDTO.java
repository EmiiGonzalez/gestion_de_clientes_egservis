package egservis.services.models.dto.cliente;

import egservis.persistence.entities.Cliente;

public record ClienteResponseDTO(Long id, String dni,
        String nombre,
        String apellido,
        String numTelefono) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(),
                cliente.getNumTelefono());
    }
}
