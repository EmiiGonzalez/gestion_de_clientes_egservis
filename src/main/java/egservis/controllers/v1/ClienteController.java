package egservis.controllers.v1;

import java.util.Optional;

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

import egservis.services.cliente.ClienteService;
import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.ClienteResponseDTO;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final PersonalizedMessage personalizedMessage;

    //GET METHODS START
    @Transactional(readOnly = true)
    @GetMapping(value = "/get", headers = "Accept=application/json")
    @ResponseBody
    public Page<ClienteResponseDTO> getAllActive(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return clienteService.getAllActive(pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/get/all", headers = "Accept=application/json")
    @ResponseBody
    public Page<ClienteResponseDTO> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return clienteService.getAll(pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/get/dni/{dni}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<ClienteResponseDTO> getByDni(@PathVariable() String dni) throws ClienteNoExistenteException {
        ClienteResponseDTO cliente = clienteService.findByDni(dni);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping(value = "/get/id/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable() Long id) throws ClienteNoExistenteException {
        ClienteResponseDTO cliente = clienteService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
    //GET METHODS END

    // POST METHOD START
    @PostMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ClienteDTO clienteDTO) throws ClienteExistenteException {
        ClienteDTO cliente = clienteService.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
    // POST METHOD END

    // PUT METHOD START
    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ClienteDTO> update(@PathVariable() Long id, @RequestBody ClienteUpdateDTO clienteUpdate) throws ClienteNoExistenteException {
        ClienteDTO cliente = clienteService.update(id, clienteUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
    // PUT METHOD END

    // DELETE LOGIC METHOD START
    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseMessage> deleteLogic(@PathVariable() Long id) throws ClienteNoExistenteException, ClienteDesactivadoException {
        String dni = clienteService.deleteLogic(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.clienteDeleted().replace("$", dni), 0));
    }

    // DELETE LOGIC METHOD END
    
}
