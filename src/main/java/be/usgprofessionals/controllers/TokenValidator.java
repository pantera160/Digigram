package be.usgprofessionals.controllers;

import java.util.Base64;

/**
 * Created by Thomas Straetmans on 25/01/2016.
 * <p>
 * Digigram for USG Professionals
 */
public class TokenValidator {

    static Boolean validateToken(String token) {
        Base64.getDecoder().decode(token);
        return true;
    }
}
