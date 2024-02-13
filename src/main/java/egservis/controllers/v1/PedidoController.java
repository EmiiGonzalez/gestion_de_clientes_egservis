package egservis.controllers.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.pedido.PedidoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class PedidoController {
    
    private PedidoService pedidoService;

    @GetMapping(value = "/get/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> obtenerPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedido(id));
    }

    @GetMapping(value = "/list/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<List<PedidoListDto>> obtenerPedidos(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidos(id));
    }
}
