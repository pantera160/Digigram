package be.usgprofessionals.Utils;

/**
 * Created by Thomas Straetmans on 27/11/15.
 *
 * Digigram for USG Professionals
 */
public class DEFAULTS {

    private static final DBTYPES dbtype = DBTYPES.LOCAL;

    public static DBTYPES getDbtype(){
        return dbtype;
    }
}
