package egservis.persistence.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
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

    public void actualizarDatos(DispositivoUpdateDTO dispositivoUpdate) {
        if (dispositivoUpdate.marca() != null && !dispositivoUpdate.marca().isEmpty()) {
            this.marca = dispositivoUpdate.marca();
        }
        if (dispositivoUpdate.modelo() != null && !dispositivoUpdate.modelo().isEmpty()) {
            this.modelo = dispositivoUpdate.modelo();
        }
        if (dispositivoUpdate.procesador() != null && !dispositivoUpdate.procesador().isEmpty()) {
            this.procesador = dispositivoUpdate.procesador();
        }
        if (dispositivoUpdate.so() != null && !dispositivoUpdate.so().isEmpty()) {
            this.so = dispositivoUpdate.so();
        }
        if (dispositivoUpdate.ram() != null && dispositivoUpdate.ram() > 0) {
            this.ram = dispositivoUpdate.ram();
        }
        if (dispositivoUpdate.almacenamiento() != null && dispositivoUpdate.almacenamiento() > 0) {
            this.almacenamiento = dispositivoUpdate.almacenamiento();
        }
        if (dispositivoUpdate.pantalla() != null && !dispositivoUpdate.pantalla().isEmpty()) {
            this.pantalla = dispositivoUpdate.pantalla();
        }
        if (dispositivoUpdate.otros() != null && !dispositivoUpdate.otros().isEmpty()) {
            this.otros = dispositivoUpdate.otros();
        }
    }
}
