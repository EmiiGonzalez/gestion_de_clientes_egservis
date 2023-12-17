package egservis.models.enums;

public enum Estado {

    INGRESADO,
    EN_PROCESO,
    FINALIZADO,
    CANCELADO;

    public static Estado fromString(String estado) {
        for (Estado e : Estado.values()) {
            if (e.name().equalsIgnoreCase(estado)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}
