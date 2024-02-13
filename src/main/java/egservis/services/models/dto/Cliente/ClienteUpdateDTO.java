package egservis.services.models.dto.cliente;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Pattern;

public record ClienteUpdateDTO(@Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe tener entre 7 y 8 dígitos.") @Nullable String dni,
                @Nullable String nombre,
                @Nullable String apellido, 
                @Nullable String numTelefono) {
}
