package egservis.services.models.dto.pedido;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PedidoDTO(
    @NotBlank
    Long idCliente,
    @NotBlank
    BigDecimal presupuesto,
    @NotBlank
    @Pattern(regexp = "INGRESADO|EN_PROCESO|FINALIZADO|CANCELADO", message = "El campo estado debe ser uno de: INGRESADO, EN_PROCESO, FINALIZADO, CANCELADO")
    String estado,
    @NotBlank
    @Pattern(regexp = "DIAGNOSTICO|FORMATEO|MANTENIMIENTO|REPARACION",
            message = "El campo servicio debe ser uno de: DIAGNOSTICO, FORMATEO, MANTENIMIENTO, REPARACION")
    String servicio
) {
}
