package egservis.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.enums.Estado;
import egservis.services.models.enums.Servicio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@Table(name = "pedidos")
@JsonIgnoreProperties({"cliente"})
public class Pedido implements Serializable{
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_pedido")
    private Long idPedido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;
    
    private BigDecimal presupuesto;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private Servicio servicio;

    private boolean activo;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido")
    @JoinColumn(name = "id_dispositivo", referencedColumnName = "id_dispositivo")
    private Dispositivo dispositivo;

    public Pedido(PedidoDTO pedido) {
        this.activo = true;
        this.fechaIngreso = LocalDate.now();
        this.presupuesto = pedido.presupuesto();
        this.servicio = Servicio.fromString(pedido.servicio());
        this.estado = Estado.fromString(pedido.estado());
    }
    
}
