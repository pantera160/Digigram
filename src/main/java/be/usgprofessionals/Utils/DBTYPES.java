package be.usgprofessionals.Utils;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public enum DBTYPES {
    LOCAL("localdb"), MYSQL("mysqldb");

    private String text;

    DBTYPES(String text){
        this.text = text;
    }

    public static DBTYPES fromString(String text) {
        if (text != null) {
            for (DBTYPES b : DBTYPES.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        throw new IllegalArgumentException("This DBType is not recognised!");
    }
}
