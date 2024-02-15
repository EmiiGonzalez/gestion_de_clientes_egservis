package egservis.services.models.dto.pedido;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PedidoDTO(
    @NotNull(message = "{idCliente.not.null}")
    Long idCliente,

    @NotNull(message = "{presupuesto.not.null}")
    BigDecimal presupuesto,

    @NotBlank(message = "{servicio.not.blank}")
    @Pattern(regexp = "DIAGNOSTICO|FORMATEO|MANTENIMIENTO|REPARACION",
            message = "{servicio.pattern}")
    String servicio
) {
}
