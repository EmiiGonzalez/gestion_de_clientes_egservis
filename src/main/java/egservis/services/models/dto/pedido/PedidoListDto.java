package egservis.services.models.dto.pedido;

import java.time.format.DateTimeFormatter;

import egservis.persistence.entities.Pedido;
import lombok.Data;

@Data
public class PedidoListDto {
    private Long id;
    private String fechaDeIngreso;
    private String estado;

    public PedidoListDto(Pedido pedido) {
        this.id = pedido.getIdPedido();
        this.fechaDeIngreso = pedido.getFechaIngreso().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.estado = pedido.getEstado().name();
    }

}
