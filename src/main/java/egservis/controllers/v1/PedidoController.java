package egservis.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.pedido.PedidoDTO;
import egservis.services.models.dto.pedido.PedidoListDto;
import egservis.services.models.dto.pedido.PedidoResponseDTO;
import egservis.services.models.dto.pedido.PedidoUpdateDTO;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoDesactivadoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaEntregaInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaIngresoInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFormatoFechaInvalidoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;
import egservis.services.pedido.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pedido")
@AllArgsConstructor
public class PedidoController {
    
    private final PedidoService pedidoService;
    private final PersonalizedMessage personalizedMessage;

    // GET METHODS START 
    @GetMapping(value = "/get/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional(readOnly = true)
    public ResponseEntity<PedidoResponseDTO> obtenerPedido(@PathVariable Long id) throws PedidoNoExisteException {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.obtenerPedido(id));
    }

    @GetMapping(value = "/list/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional(readOnly = true)
    public Page<PedidoListDto> obtenerPedidos(@PathVariable Long id,@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return pedidoService.obtenerPedidos(id, pageable);
    }
    // GET METHODS END

    // POST METHOD START
    @PostMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<PedidoResponseDTO> save(@RequestBody @Valid PedidoDTO pedidoDTO) throws PedidoNoExisteException, ClienteNoExistenteException {
        PedidoResponseDTO pedido = pedidoService.save(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
    // POST METHOD END

    // DELETE LOGIC METHOD START
    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseMessage> deleteLogic(@PathVariable Long id) throws PedidoNoExisteException, PedidoDesactivadoException {
        pedidoService.deleteLogic(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.pedidoDeleted().replace("$", id.toString()), 0));
    }
    // DELETE LOGIC METHOD END

    // PUT METHOD START
    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<PedidoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PedidoUpdateDTO pedidoDTO) throws PedidoNoExisteException, PedidoFormatoFechaInvalidoException, PedidoFechaEntregaInvalidaException, PedidoFechaIngresoInvalidaException {
        PedidoResponseDTO pedido = pedidoService.update(id, pedidoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }
    // PUT METHOD END
}
