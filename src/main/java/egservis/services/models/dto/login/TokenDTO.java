package egservis.services.models.dto.login;

public record TokenDTO(
    String token
) {
    public TokenDTO(String token) {
        this.token = token;
    }
}
