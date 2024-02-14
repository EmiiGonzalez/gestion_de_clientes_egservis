package egservis.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;
import egservis.services.pedido.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class PedidoController {
    
    private PedidoService pedidoService;

    @GetMapping(value = "/get/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional(readOnly = true)
    public ResponseEntity<PedidoResponseDTO> obtenerPedido(@PathVariable Long id) throws PedidoNoExisteException {
        return ResponseEntity.ok(pedidoService.obtenerPedido(id));
    }

    @GetMapping(value = "/list/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional(readOnly = true)
    public Page<PedidoListDto> obtenerPedidos(@PathVariable Long id,@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return pedidoService.obtenerPedidos(id, pageable);
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<PedidoResponseDTO> save(@RequestBody @Valid PedidoDTO pedidoDTO) throws PedidoNoExisteException, ClienteNoExistenteException {
        PedidoResponseDTO pedido = pedidoService.save(pedidoDTO);
        return ResponseEntity.status(201).body(pedido);
    }
}
