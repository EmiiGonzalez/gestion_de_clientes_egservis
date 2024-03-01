package egservis.services.models.dto.dispositivo;

import java.util.List;

public record DispositivoCountResponseDTO(
    Long total,
    List<DispositivoCountMothDTO> dispositivos) {
    
    public DispositivoCountResponseDTO(List<DispositivoCountMothDTO> dispositivos) {
        this(dispositivos.stream().mapToLong(d -> d.cantidadDispositivos()).sum(), dispositivos);
    }
}
