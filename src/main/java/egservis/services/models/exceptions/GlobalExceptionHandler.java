package egservis.services.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler  {

    private PersonalizedMessage personalizedMessage;
    
    @ResponseBody
    @ExceptionHandler(ClienteNoExistenteException.class)
    public ResponseEntity<ResponseMessage> userNotFound(ClienteNoExistenteException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(personalizedMessage.clienteNotFound(), 1));
    }

    @ResponseBody
    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity<ResponseMessage> userAlreadyExists(ClienteExistenteException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(personalizedMessage.clienteAlreadyExists(), 2));
    }

    @ResponseBody
    @ExceptionHandler(ClienteDesactivadoException.class)
    public ResponseEntity<ResponseMessage> userAlreadyDisabled(ClienteDesactivadoException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(personalizedMessage.clienteAlreadyDisabled(), 3));
    }

}
