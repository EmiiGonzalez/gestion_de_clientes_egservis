package egservis.services.models.enums;

public enum Servicio {
    
    DIAGNOSTICO,
    FORMATEO,
    MANTENIMIENTO,
    REPARACION;

    public static Servicio fromString(String servicio) {
        for (Servicio s : Servicio.values()) {
            if (s.name().equalsIgnoreCase(servicio)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Servicio no válido: " + servicio);
    }
}
