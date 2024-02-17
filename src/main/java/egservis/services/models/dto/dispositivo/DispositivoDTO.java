package egservis.services.models.dto.dispositivo;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DispositivoDTO(
    @NotBlank(message = "{marca.not.blank}")
    String marca,
    @NotBlank(message = "{modelo.not.blank}")
    String modelo,
    @NotBlank(message = "{procesador.not.blank}")
    String procesador,
    @NotBlank(message = "{so.not.blank}")
    String so,
    @NotNull(message = "{ram.not.blank}")
    Integer ram,
    @NotNull(message = "{almacenamiento.not.blank}")
    Integer almacenamiento,
    @NotBlank(message = "{pantalla.not.blank}")
    String pantalla,
    @Nullable
    String otros
) {
} 