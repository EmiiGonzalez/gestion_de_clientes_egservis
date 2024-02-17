package egservis.services.models.dto.dispositivo;

import egservis.persistence.entities.Dispositivo;

public record DispositivoResponseDTO(
    Long id,
    String marca,
    String modelo,
    String procesador,
    String so,
    Integer ram,
    Integer almacenamiento,
    String pantalla,
    String otros
) {

    public DispositivoResponseDTO(Dispositivo dispositivo) {
        this(dispositivo.getIdDispositivo(), dispositivo.getMarca(), dispositivo.getModelo(),
                dispositivo.getProcesador(), dispositivo.getSo(), dispositivo.getRam(),
                dispositivo.getAlmacenamiento(), dispositivo.getPantalla(), dispositivo.getOtros());
    }
    
}
