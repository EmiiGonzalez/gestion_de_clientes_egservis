package egservis.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.dto.pedido.PedidoCompleteDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "dispositivos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
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

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.PERSIST)
    private List<Pedido> pedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    public Dispositivo(DispositivoDTO dispositivoDTO) {
        this.marca = dispositivoDTO.marca();
        this.modelo = dispositivoDTO.modelo();
        this.procesador = dispositivoDTO.procesador();
        this.so = dispositivoDTO.so();
        this.ram = dispositivoDTO.ram();
        this.almacenamiento = dispositivoDTO.almacenamiento();
        this.pantalla = dispositivoDTO.pantalla();
        this.otros = dispositivoDTO.otros();
        this.pedido = new ArrayList<>();
    }

    public Dispositivo() {
        this.pedido = new ArrayList<>();
    }

    public Dispositivo(@Valid PedidoCompleteDTO pedidoDTO) {
        this.marca = pedidoDTO.marca();
        this.modelo = pedidoDTO.modelo();
        this.procesador = pedidoDTO.procesador();
        this.so = pedidoDTO.so();
        this.ram = pedidoDTO.ram();
        this.almacenamiento = pedidoDTO.almacenamiento();
        this.pantalla = pedidoDTO.pantalla();
        this.otros = pedidoDTO.otros();
        this.pedido = new ArrayList<>();
    }

    public void addPedido(Pedido pedido) {
        this.pedido.add(pedido);
        pedido.setDispositivo(this);
    }


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
