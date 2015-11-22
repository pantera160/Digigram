package be.usgprofessionals.Utils;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;

/**
 * Created by Pantera on 17/11/15.
 */
public class EID {

    private String id;

    public EID(String id) throws EIDFormatIncorrectException {
        if (id.matches("[*0-9*]") && id.matches("[*\\p{L}*]")) {
            this.id = id;
        }
        else throw new EIDFormatIncorrectException();
    }

    public String getId() {
        return this.id;
    }
}
