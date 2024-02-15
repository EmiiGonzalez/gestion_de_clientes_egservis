package egservis.services.models.dto.cliente;


import egservis.persistence.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(

        @NotBlank(message = "{dni.not.blank}")
        @Pattern(regexp = "^[0-9]{7,8}$", message = "{dni.pattern}")
        String dni,

        @NotBlank(message = "{nombre.not.blank}")
        String nombre,

        @NotBlank(message = "{apellido.not.blank}")
        String apellido,

        @NotBlank(message = "{numTelefono.not.blank}")
        String numTelefono) {

    public ClienteDTO(Cliente cliente) {
        this(cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.getNumTelefono());
    }
}
