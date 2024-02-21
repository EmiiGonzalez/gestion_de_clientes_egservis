package egservis.services.models.dto.pedido;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import egservis.persistence.entities.Pedido;
import lombok.Data;

@Data
public class PedidoResponseDTO {
    private Long id;
    private String fechaDeIngreso;
    private String fechaDeEntrega;
    private BigDecimal presupuesto;
    private String estado;
    private String servicio;
    private String cliente;
    private String dni;

    public PedidoResponseDTO(Pedido pedido){
        this.id = pedido.getIdPedido();
        this.servicio = pedido.getServicio().name();
        this.fechaDeIngreso = pedido.getFechaIngreso().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.fechaDeEntrega = pedido.getFechaEntrega() == null ? null : pedido.getFechaEntrega().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.presupuesto = pedido.getPresupuesto();
        this.estado = pedido.getEstado().name();
        this.cliente = pedido.getDispositivo().getCliente().getNombre() + " " + pedido.getDispositivo().getCliente().getApellido();
        this.dni = pedido.getDispositivo().getCliente().getDni();
    }
}
