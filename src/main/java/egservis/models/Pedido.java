package egservis.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egservis.models.enums.Estado;
import egservis.models.enums.Servicio;
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
    private Long idPedido;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaIngreso;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaEntrega;
    private BigDecimal presupuesto;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Enumerated(EnumType.STRING)
    private Servicio servicio;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido")
    @JoinColumn(name = "idDispositivo", referencedColumnName = "idDispositivo")
    private Dispositivo dispositivo;
    
}
