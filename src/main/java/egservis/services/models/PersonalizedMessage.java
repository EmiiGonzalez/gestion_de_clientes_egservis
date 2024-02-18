package egservis.services.models;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PersonalizedMessage {
    private final MessageSource messageSource;

    // CLIENTE MENSAJES START
    public String clienteNotFound() {
        return messageSource.getMessage("client.not.found", null, null);
    }

    public String clienteDeleted() {
        return messageSource.getMessage("client.delete.success", null, null);
    }

    public String clienteAlreadyExists() {
        return messageSource.getMessage("client.already.exists", null, null);
    }

    public String clienteAlreadyDisabled() {
        return messageSource.getMessage("client.already.disabled", null, null);
    }

    // CLIENTE MENSAJES END

    // PEDIDO MENSAJES START

    public String pedidoNotFound() {
        return messageSource.getMessage("pedido.not.found", null, null);
    }

    public String pedidoAlreadyDisabled() {
        return messageSource.getMessage("pedido.already.disabled", null, null);
    }

    public String pedidoDeleted() {
        return messageSource.getMessage("pedido.delete.success", null, null);
    }

    public String dateFormatInvalid() {
        return messageSource.getMessage("date.format.invalid", null, null);
    }

    public String pedidoDateEntregaInvalid() {
        return messageSource.getMessage("pedido.date.entrega.invalid", null, null);
    }

    public String pedidoDateIngresoInvalid() {
        return messageSource.getMessage("pedido.date.ingreso.invalid", null, null);
    }

    public String dispositivoDeleted() {
        return messageSource.getMessage("dispositivo.delete.success", null, null);
    }
}
