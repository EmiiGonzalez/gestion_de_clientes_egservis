package egservis.services.models.dto.cliente;

import egservis.persistence.entities.Cliente;

public record DatosListadoClienteDTO(Long id, String dni,
        String nombre,
        String apellido,
        String numTelefono) {

    public DatosListadoClienteDTO(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(),
                cliente.getNumTelefono());
    }
}
