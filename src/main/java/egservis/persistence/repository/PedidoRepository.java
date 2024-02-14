package egservis.persistence.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import egservis.persistence.entities.Pedido;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT new egservis.services.models.dto.pedido.PedidoResponseDTO(p) FROM Pedido p WHERE p.idPedido=?1 AND p.activo=true")
    public Optional<PedidoResponseDTO> obtenerPedido(Long id);

    @Query("SELECT new egservis.services.models.dto.pedido.PedidoListDto(p) FROM Pedido p WHERE p.cliente.id=?1 AND p.activo=true")
    public Page<PedidoListDto> obtenerListaPedidos(Long id, Pageable pageable);
   
} 
