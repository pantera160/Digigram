package be.usgprofessionals.Utils;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class EIDTest {

    @Test
    public void EIDcreatesCorrectWithCorrectString() throws Exception{
        EID id = new EID("abcde12345ZER123");
        assertEquals("abcde12345ZER123", id.getId());
    }

    @Test(expected = EIDFormatIncorrectException.class)
    public void EIDthrowsExceptionwhenNoNumber() throws Exception{
        EID id = new EID("aedvrezasderftez");
        assertEquals("aedvrezasderftez", id.getId());
    }
    @Test(expected = EIDFormatIncorrectException.class)
    public void EIDThrowsExceptionwhenNoLetter() throws Exception{
        EID id = new EID("1234567890123456");
        assertEquals("1234567890123456", id.getId());
    }

    @Test(expected = EIDFormatIncorrectException.class)
    public void EIDThowsExceptionwhenToShort() throws Exception{
        EID id = new EID("1234567AZER");
        assertEquals("1234567AZER", id.getId());
    }


}