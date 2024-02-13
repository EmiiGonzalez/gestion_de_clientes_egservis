package egservis.controllers.v1;

import java.util.Optional;

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

import egservis.persistence.entities.Cliente;
import egservis.services.cliente.ClienteService;
import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.cliente.ClienteDTO;
import egservis.services.models.dto.cliente.ClienteUpdateDTO;
import egservis.services.models.dto.cliente.DatosListadoCliente;
import egservis.services.models.dto.response.ResponseMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private PersonalizedMessage personalizedMessage;

    @GetMapping(value = "/get", headers = "Accept=application/json")
    public Page<DatosListadoCliente> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return clienteService.getAll(pageable);
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody @Valid ClienteDTO clienteDTO) {
        try {
            if (clienteService.existsByDni(clienteDTO.dni())) {
            String mensaje = "Ya existe un cliente con el dni: " + clienteDTO.dni();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
        } else {
            Cliente cliente = new Cliente(clienteDTO);
            clienteService.save(cliente);
            String mensaje = "Se ha guardado el cliente con el dni: " + clienteDTO.dni();
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
        }
        } catch (Exception e) {
            String mensaje = "Hubo un error al procesar la petición" + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }
    }

    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> update(@PathVariable() Long id, @RequestBody ClienteUpdateDTO clienteUpdate) {
        Optional<Cliente> cliente = clienteService.update(id, clienteUpdate);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.clienteNotFound(), 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ClienteDTO(cliente.get()));
    }

    // DELETE LOGIC
    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseMessage> deleteLogic(@PathVariable() Long id) {
        
        Optional<Cliente> cliente = clienteService.deleteLogic(id);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.clienteNotFound(), 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.clienteDeleted(), 0));
    }
    
}
