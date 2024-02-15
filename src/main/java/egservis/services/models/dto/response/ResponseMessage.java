package egservis.services.models.dto.response;

public record ResponseMessage(
    String message,
    Integer errorCode
) {

} 