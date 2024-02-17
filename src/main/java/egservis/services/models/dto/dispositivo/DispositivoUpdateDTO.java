package egservis.services.models.dto.dispositivo;

import io.micrometer.common.lang.Nullable;

public record DispositivoUpdateDTO(
    @Nullable
    String marca,
    @Nullable
    String modelo,
    @Nullable
    String procesador,
    @Nullable
    String so,
    @Nullable
    Integer ram,
    @Nullable
    Integer almacenamiento,
    @Nullable
    String pantalla,
    @Nullable
    String otros
) {
}