package egservis.services.models.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank
    String login,
    @NotBlank
    String clave
) {
    
}
