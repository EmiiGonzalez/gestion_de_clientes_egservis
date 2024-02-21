package egservis.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    private String dni;
    private String nombre;
    private String apellido;
    @Column(name = "num_telefono")
    private String numTelefono;
    private Boolean activo;
   
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Dispositivo> dispositivos; 
    
    public Cliente(String dni, String nombre, String apellido, String numTelefono) {
        this.activo = true;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numTelefono = numTelefono;
        this.dispositivos = new ArrayList<>();
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.activo = true;
        this.dni = clienteDTO.dni();
        this.nombre = clienteDTO.nombre();
        this.apellido = clienteDTO.apellido();
        this.numTelefono = clienteDTO.numTelefono();
        this.dispositivos = new ArrayList<>();
    }

    public Cliente(){
        this.dispositivos = new ArrayList<>();
    }

    public void addDispositivo(Dispositivo dispositivo) {
        this.dispositivos.add(dispositivo);
        dispositivo.setCliente(this);
    }

    public void actualizarDatos(ClienteUpdateDTO clienteUpdate) {
        if(clienteUpdate.dni() != null && !clienteUpdate.dni().isEmpty()) {
            this.dni = clienteUpdate.dni();
        }
        if(clienteUpdate.nombre() != null && !clienteUpdate.nombre().isEmpty()) {
            this.nombre = clienteUpdate.nombre();
        }
        if(clienteUpdate.apellido() != null && !clienteUpdate.apellido().isEmpty()) {
            this.apellido = clienteUpdate.apellido();
        }
        if(clienteUpdate.numTelefono() != null && !clienteUpdate.numTelefono().isEmpty()) {
            this.numTelefono = clienteUpdate.numTelefono();
        }
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }
}
