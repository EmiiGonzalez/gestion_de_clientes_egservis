package egservis.services.pedido;

import java.util.List;

import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;

public interface PedidoService {
    PedidoResponseDTO obtenerPedido(Long id);

    List<PedidoListDto> obtenerPedidos(Long id);
}
