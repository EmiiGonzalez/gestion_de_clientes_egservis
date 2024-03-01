package egservis.controllers.v1;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.services.dispositivo.DispositivoService;
import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.dispositivo.DispositivoCountMothDTO;
import egservis.services.models.dto.dispositivo.DispositivoDTO;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.dto.pedido.PedidoCompleteDTO;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/dispositivo")
@AllArgsConstructor
public class DispositivoController {
    
    private DispositivoService dispositivoService;
    private PersonalizedMessage personalizedMessage;

    // GET METHODS START 
    @GetMapping(value = "/get", headers = "Accept=application/json")
    @ResponseBody
    public Page<DispositivoResponseDTO> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return dispositivoService.getAll(pageable);
    }

    @GetMapping(value = "/get/id/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<DispositivoResponseDTO> getById(@PathVariable() Long id) throws DispositivoNoExisteException {
        DispositivoResponseDTO dispositivo = dispositivoService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dispositivo);
    }

    @GetMapping(value = "/get/cliente/id/{id}", headers = "Accept=application/json")
    @ResponseBody
    public Page<DispositivoResponseDTO> getByClienteId(@PathVariable() Long id, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return dispositivoService.getByClienteId(id, pageable);
    }

    @GetMapping(value = "/get/cliente/dni/{dni}", headers = "Accept=application/json")
    @ResponseBody
    public Page<DispositivoResponseDTO> getByClienteDni(@PathVariable() Long dni, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return dispositivoService.getByClienteDni(dni, pageable);
    }

    @GetMapping(value = "/get/count", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<List<DispositivoCountMothDTO>> getCount() {
        return ResponseEntity.status(HttpStatus.OK).body(dispositivoService.findAllByMes());
    }
    // GET METHODS END

    // PUT METHOD START

    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<DispositivoResponseDTO> update(@PathVariable() Long id, @RequestBody DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException {
        DispositivoResponseDTO dispositivo = dispositivoService.update(id,dispositivoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dispositivo);
    }

    // PUT METHOD END

    // DELETE METHOD START

    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {
        dispositivoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.dispositivoDeleted().replace("$", id.toString()), 0));
    }

    // DELETE METHOD END

    // POST METHOD START

    @PostMapping(value = "/new", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<DispositivoResponseDTO> save(@RequestBody @Valid PedidoCompleteDTO pedidoDTO) throws ClienteNoExistenteException {
        DispositivoResponseDTO pedido = dispositivoService.saveComplete(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<DispositivoResponseDTO> save(@RequestBody @Valid DispositivoDTO dispositivoDTO) throws DispositivoNoExisteException, ClienteNoExistenteException {
        DispositivoResponseDTO dispositivo = dispositivoService.save(dispositivoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dispositivo);
    }

    // POST METHOD END
}
