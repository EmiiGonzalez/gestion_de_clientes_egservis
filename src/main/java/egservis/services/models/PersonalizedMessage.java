package egservis.services.models;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PersonalizedMessage {
    private final MessageSource messageSource;

    public String clienteNotFound() {
        return messageSource.getMessage("client.not.found", null, null);
    }

    public String clienteDeleted() {
        return messageSource.getMessage("client.delete.success", null, null);
    }
}
