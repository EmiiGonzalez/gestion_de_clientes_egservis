package egservis.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import egservis.Dto.ClienteDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String dni;
    private String nombre;
    private String apellido;
    private String numTelefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Pedido> pedidos = new ArrayList<>(); 

    public Cliente(String dni, String nombre, String apellido, String numTelefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numTelefono = numTelefono;
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.dni = clienteDTO.dni();
        this.nombre = clienteDTO.nombre();
        this.apellido = clienteDTO.apellido();
        this.numTelefono = clienteDTO.numTelefono();
    }

    public void addPedidos(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this);
    }
}
