package egservis.services.pedido;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.models.dto.pedido.PedidoUpdateDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoDesactivadoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaEntregaInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaIngresoInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFormatoFechaInvalidoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;

@Service
public interface PedidoService {
    PedidoResponseDTO obtenerPedido(Long id) throws PedidoNoExisteException;

    Page<PedidoListDto> obtenerPedidos(Long id, Pageable pageable);

    PedidoResponseDTO save(PedidoDTO pedido) throws ClienteNoExistenteException, DispositivoNoExisteException;

    void deleteLogic(Long id) throws PedidoNoExisteException, PedidoDesactivadoException;

    public PedidoResponseDTO update(Long id, PedidoUpdateDTO pedido) throws PedidoNoExisteException, PedidoFormatoFechaInvalidoException, PedidoFechaEntregaInvalidaException, PedidoFechaIngresoInvalidaException;

}
