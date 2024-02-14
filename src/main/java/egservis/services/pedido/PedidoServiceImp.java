package egservis.services.pedido;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egservis.persistence.entities.Pedido;
import egservis.persistence.repository.PedidoRepository;
import egservis.services.cliente.ClienteServiceImp;
import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoServiceImp implements PedidoService{

    private PedidoRepository pedidoRepository;
    private ClienteServiceImp clienteService;

    @Override
    public PedidoResponseDTO obtenerPedido(Long id) throws PedidoNoExisteException {
        Optional<PedidoResponseDTO> pedido = pedidoRepository.obtenerPedido(id);

        if(!pedido.isPresent()){
            throw new PedidoNoExisteException("El pedido con el id " + id + " no existe");
        }

        return pedido.get();
    }

    @Override
    public Page<PedidoListDto> obtenerPedidos(Long id, Pageable pageable) {
        return pedidoRepository.obtenerListaPedidos(id, pageable);
    }

    @Override
    public PedidoResponseDTO save(PedidoDTO pedido) throws ClienteNoExistenteException{
        Pedido p = new Pedido(pedido);

        clienteService.addPedido(p, pedido.idCliente());
        
        return new PedidoResponseDTO(pedidoRepository.save(p));
    }
    
}
