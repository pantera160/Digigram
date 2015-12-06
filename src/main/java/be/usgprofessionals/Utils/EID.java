package be.usgprofessionals.Utils;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
public class EID {

    private String id;

    public EID(String id) throws EIDFormatIncorrectException {
        if (id.matches("(.*)[0-9](.*)") && id.matches("(.*)\\p{L}(.*)") && id.length() == 16) {
            this.id = id;
        }
        else throw new EIDFormatIncorrectException();
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object o){
        if(o instanceof EID){
            if(((EID) o).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
