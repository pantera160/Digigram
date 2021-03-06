package be.usgprofessionals.Exceptions;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
public class EIDFormatIncorrectException extends Exception {

    public EIDFormatIncorrectException() {
        super("The format you used when creating an EID object was incorrect. EID's consists of a String of 16 characters containing both letters and numbers.");
    }
}
