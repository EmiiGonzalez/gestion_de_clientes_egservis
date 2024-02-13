package egservis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import egservis.Entities.Pedido;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT new egservis.services.models.dto.pedido.PedidoResponseDTO(p) FROM Pedido p WHERE p.idPedido=?1")
    public Optional<PedidoResponseDTO> obtenerPedido(Long id);

    @Query("SELECT new egservis.services.models.dto.pedido.PedidoListDto(p) FROM Pedido p WHERE p.cliente.id=?1")
    public List<PedidoListDto> obtenerListaPedidos(Long id);

} 
