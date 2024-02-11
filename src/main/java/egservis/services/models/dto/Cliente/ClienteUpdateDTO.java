package egservis.services.models.dto.Cliente;

import jakarta.validation.constraints.NotNull;

public record ClienteUpdateDTO(@NotNull Long id, String dni, String nombre, String apellido, String numTelefono) {
}
