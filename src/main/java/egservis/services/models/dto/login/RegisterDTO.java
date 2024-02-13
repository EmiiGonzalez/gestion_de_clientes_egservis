package egservis.services.models.dto.login;

import jakarta.validation.constraints.NotBlank;


public record RegisterDTO(
    @NotBlank
    String login,
    @NotBlank
    String clave
) {
    
}
