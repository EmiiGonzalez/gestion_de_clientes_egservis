package egservis.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dispositivos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@NoArgsConstructor
@JsonIgnoreProperties({"pedido"})
public class Dispositivo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_dispositivo")
    private Long idDispositivo;
    private String marca;
    private String modelo;
    private String procesador;
    private String so;
    private Integer ram;
    private Integer almacenamiento;
    private String pantalla;
    private String otros;
    
    @OneToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private Pedido pedido;
}
