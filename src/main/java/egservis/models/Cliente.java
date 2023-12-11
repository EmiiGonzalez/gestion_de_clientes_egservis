package egservis.models;

import egservis.Dto.ClienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    @Column(name = "numTelefono")
    private String numTelefono;

    public Cliente() {
    }

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
}
