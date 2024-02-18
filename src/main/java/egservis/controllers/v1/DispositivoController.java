package egservis.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egservis.services.dispositivo.DispositivoService;
import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.dispositivo.DispositivoResponseDTO;
import egservis.services.models.dto.dispositivo.DispositivoUpdateDTO;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.dispositivoExceptions.DispositivoNoExisteException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/dispositivo")
@AllArgsConstructor
public class DispositivoController {
    
    private DispositivoService dispositivoService;
    private PersonalizedMessage personalizedMessage;

    @GetMapping(value = "/get/all", headers = "Accept=application/json")
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

    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<DispositivoResponseDTO> update(@PathVariable() Long id, @RequestBody DispositivoUpdateDTO dispositivoDTO) throws DispositivoNoExisteException {
        DispositivoResponseDTO dispositivo = dispositivoService.update(id,dispositivoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dispositivo);
    }

    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseMessage> deletec(@PathVariable Long id) {
        dispositivoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(personalizedMessage.dispositivoDeleted().replace("$", id.toString()), 0));
    }

}
