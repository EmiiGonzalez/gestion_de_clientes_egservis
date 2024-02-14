package egservis.services.pedido;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;

public interface PedidoService {
    PedidoResponseDTO obtenerPedido(Long id) throws PedidoNoExisteException;

    Page<PedidoListDto> obtenerPedidos(Long id, Pageable pageable);

    PedidoResponseDTO save(PedidoDTO pedido) throws ClienteNoExistenteException;

}
