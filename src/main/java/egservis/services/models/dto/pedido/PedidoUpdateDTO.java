package egservis.services.models.dto.pedido;

import java.math.BigDecimal;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Pattern;

public record PedidoUpdateDTO(
        @Nullable String fechaIngreso,
        @Nullable String fechaEntrega,
        @Nullable BigDecimal presupuesto,
        @Nullable @Pattern(regexp = "INGRESADO|EN_PROCESO|FINALIZADO|CANCELADO", message = "El campo estado debe ser uno de: INGRESADO, EN_PROCESO, FINALIZADO, CANCELADO") String estado,
        @Nullable @Pattern(regexp = "DIAGNOSTICO|FORMATEO|MANTENIMIENTO|REPARACION", message = "El campo servicio debe ser uno de: DIAGNOSTICO, FORMATEO, MANTENIMIENTO, REPARACION") String servicio) {
}
