package egservis.services.models.dto.cliente;

import egservis.persistence.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(

        @NotBlank    
        @Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe tener entre 7 y 8 dígitos.")
        String dni,

        @NotBlank
        String nombre,

        @NotBlank
        String apellido,

        @NotBlank
        String numTelefono) {

    public ClienteDTO(Cliente cliente) {
        this(cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.getNumTelefono());
    }
}
