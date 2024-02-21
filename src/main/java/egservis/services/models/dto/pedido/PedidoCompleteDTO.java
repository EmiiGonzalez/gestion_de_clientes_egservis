package egservis.services.models.dto.pedido;

import java.math.BigDecimal;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Este DTO se utiliza para la creacion de un pedido y un dispositivo a un cliente de un solo request
public record PedidoCompleteDTO(
    @NotNull(message = "{idCliente.not.null}")
    Long idCliente,

    @NotNull(message = "{presupuesto.not.null}")
    BigDecimal presupuesto,

    @NotBlank(message = "{servicio.not.blank}")
    @Pattern(regexp = "DIAGNOSTICO|FORMATEO|MANTENIMIENTO|REPARACION",
            message = "{servicio.pattern}")
    String servicio,

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