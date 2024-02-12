package egservis.services.models.dto.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteUpdateDTO(@NotNull Long id,@NotNull  @Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe tener entre 7 y 8 dígitos.") String dni,@NotBlank String nombre, @NotBlank String apellido, @NotBlank String numTelefono) {
}
