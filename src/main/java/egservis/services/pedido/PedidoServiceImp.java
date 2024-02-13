package egservis.services.pedido;

import java.util.List;

import org.springframework.stereotype.Service;

import egservis.repository.PedidoRepository;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoServiceImp implements PedidoService{

    private PedidoRepository pedidoRepository;

    @Override
    public PedidoResponseDTO obtenerPedido(Long id) {
        return pedidoRepository.obtenerPedido(id).orElse(null);
    }

    @Override
    public List<PedidoListDto> obtenerPedidos(Long id) {
        return pedidoRepository.obtenerListaPedidos(id);
    }
    
}
