package egservis.services.models.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import egservis.services.models.PersonalizedMessage;
import egservis.services.models.dto.response.ResponseMessage;
import egservis.services.models.exceptions.clienteExceptions.ClienteDesactivadoException;
import egservis.services.models.exceptions.clienteExceptions.ClienteExistenteException;
import egservis.services.models.exceptions.clienteExceptions.ClienteNoExistenteException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoDesactivadoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaEntregaInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFechaIngresoInvalidaException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoFormatoFechaInvalidoException;
import egservis.services.models.exceptions.pedidoExceptions.PedidoNoExisteException;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler  {

    private PersonalizedMessage personalizedMessage;

    // VALIDATION EXCEPTIONS DTOS START
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseMessage>> validationError(MethodArgumentNotValidException e) {
        System.out.println(e);

        List<ResponseMessage> errorsList = new ArrayList<>();
        e.getFieldErrors().stream().forEach(error -> {
            ResponseMessage responseMessage = new ResponseMessage(error.getDefaultMessage(), 7);
            errorsList.add(responseMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsList);
    }

    // VALIDATION EXCEPTIONS DTOS END

    // CLIENTE EXCEPTIONS START
    
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
    
    // CLIENTE EXCEPTIONS END

    // PEDIDO EXCEPTIONS START

    @ResponseBody
    @ExceptionHandler(PedidoNoExisteException.class)
    public ResponseEntity<ResponseMessage> pedidoNotFound(PedidoNoExisteException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(personalizedMessage.pedidoNotFound(), 2));
    }

    @ResponseBody
    @ExceptionHandler(PedidoDesactivadoException.class)
    public ResponseEntity<ResponseMessage> pedidoAlreadyDisabled(PedidoDesactivadoException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(personalizedMessage.pedidoAlreadyDisabled(), 3));
    }

    @ResponseBody
    @ExceptionHandler(PedidoFormatoFechaInvalidoException.class)
    public ResponseEntity<ResponseMessage> dateFormatInvalid(PedidoFormatoFechaInvalidoException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(personalizedMessage.dateFormatInvalid(), 4));
    }

    @ResponseBody
    @ExceptionHandler(PedidoFechaEntregaInvalidaException.class)
    public ResponseEntity<ResponseMessage> pedidoDateEntregaInvalid(PedidoFechaEntregaInvalidaException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(personalizedMessage.pedidoDateEntregaInvalid(), 5));
    }

    @ResponseBody
    @ExceptionHandler(PedidoFechaIngresoInvalidaException.class)
    public ResponseEntity<ResponseMessage> pedidoDateIngresoInvalid(PedidoFechaIngresoInvalidaException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(personalizedMessage.pedidoDateIngresoInvalid(), 6));
    }
    
    // PEDIDO EXCEPTIONS END

}
