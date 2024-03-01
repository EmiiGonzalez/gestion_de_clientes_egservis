package egservis.services.models.dto.cliente;

import java.util.List;

public record ClienteCountResponseDTO(
    Long total,
    List<ClientesCountMonthDTO> clientes

) {
    public ClienteCountResponseDTO(List<ClientesCountMonthDTO> clientes) {
        this(
            clientes.stream().mapToLong(c -> c.cantidadClientes()).sum(), 
            clientes);
    }
} 