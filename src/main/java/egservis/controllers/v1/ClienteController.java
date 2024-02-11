package egservis.controllers.v1;

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

import egservis.Entities.Cliente;
import egservis.services.Cliente.ClienteService;
import egservis.services.Cliente.ClienteServiceImp;
import egservis.services.models.dto.Cliente.ClienteDTO;
import egservis.services.models.dto.Cliente.ClienteUpdateDTO;
import egservis.services.models.dto.Cliente.DatosListadoCliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteServiceImp clienteService) {
        this.clienteService = clienteService;
    }

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

    @PutMapping(value = "/update", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ClienteUpdateDTO clienteUpdate ) {
        if (clienteService.existsById(clienteUpdate.id())) {
            Cliente cliente = clienteService.findById(clienteUpdate.id());
            cliente.actualizarDatos(clienteUpdate);
            String mensaje = "Se ha actualizado el cliente con el dni: " + clienteUpdate.dni();
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        } else {
            String mensaje = "No existe un cliente con el dni: " + clienteUpdate.dni();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    // DELETE LOGIC
    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> delete(@PathVariable() Long id) {
        if (clienteService.existsById(id)) {
            Cliente cliente = clienteService.findById(id);
            if (cliente.getActivo()) {
                cliente.desactivar();
                String mensaje = "Se ha eliminado el cliente con el id: " + id;
                return ResponseEntity.status(HttpStatus.OK).body(mensaje);
            } else {
                String mensaje = "El cliente con el id: " + id + " ya no existe";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }
        } else {
            String mensaje = "No existe un cliente con el id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        
    }
    
}
