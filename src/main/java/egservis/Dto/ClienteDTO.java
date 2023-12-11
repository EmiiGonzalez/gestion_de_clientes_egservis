package egservis.Dto;

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
}
