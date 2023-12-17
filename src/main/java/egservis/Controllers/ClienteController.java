package egservis.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.Dto.ClienteDTO;
import egservis.models.Cliente;
import egservis.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/get", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
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
    
}
