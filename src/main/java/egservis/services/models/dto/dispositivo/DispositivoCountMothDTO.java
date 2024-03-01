package egservis.services.models.dto.dispositivo;

import egservis.services.models.enums.Months;

public record DispositivoCountMothDTO(Integer numMes,
String mes,
Long cantidadDispositivos) {
    public DispositivoCountMothDTO(Integer numMes, Long cantidadDispositivos) {
        this(numMes, Months.fromInteger(numMes), cantidadDispositivos);
    }
}
