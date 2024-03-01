package egservis.services.models.enums;

public enum Months {
    ENERO,
    FEBRERO,
    MARZO,
    ABRIL,
    MAYO,
    JUNIO,
    JULIO,
    AGOSTO,
    SEPTIEMBRE,
    OCTUBRE,
    NOVIEMBRE,
    DICIEMBRE;

    public static Months fromString(String month) {
        for (Months m : Months.values()) {
            if (m.name().equalsIgnoreCase(month)) {
                return m;
            }
        }
        return null;
    }

    public static String fromInteger(int month) {
        return Months.values()[month - 1].name().substring(0, 1).toUpperCase()
                + Months.values()[month - 1].name().substring(1).toLowerCase();
    }
}
