package egservis.services.models.dto.cliente;

import egservis.services.models.enums.Months;

public record ClientesCountMonthDTO(
        Integer numMes,
        String mes,
        Long cantidadClientes) {
    public ClientesCountMonthDTO(Integer numMes, Long cantidadClientes) {
        this(numMes, Months.fromInteger(numMes), cantidadClientes);
    }
}