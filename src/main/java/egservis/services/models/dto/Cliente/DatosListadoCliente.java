package egservis.services.models.dto.cliente;

import egservis.Entities.Cliente;

public record DatosListadoCliente(Long id, String dni,
        String nombre,
        String apellido,
        String numTelefono) {

    public DatosListadoCliente(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(),
                cliente.getNumTelefono());
    }
}
