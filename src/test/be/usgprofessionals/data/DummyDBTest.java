package be.usgprofessionals.data;


import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.EID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class DummyDBTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetBasicUser() throws Exception {
        DummyDB db = DummyDB.getInstance();
        BasicUserProfile thomas = db.getBasicUser(new EID("12345abcde6789ts"));
        assertEquals("thomas", thomas.getFirstName().toLowerCase());
        assertEquals("straetmans", thomas.getLastName().toLowerCase());
        assertEquals("fun", thomas.getUniqueProperty());
        assertEquals("USG Professionals", thomas.getEmployer().getName());
        assertTrue(thomas.isIntern());
    }

    @Test
    public void testGetAdvancedUser() throws Exception {
        DummyDB db = DummyDB.getInstance();
        AdvancedUserProfile thomas = db.getAdvancedUser(new EID("12345abcde6789ts"));
        assertEquals("thomas", thomas.getFirstName().toLowerCase());
        assertEquals("straetmans", thomas.getLastName().toLowerCase());
        assertEquals("thomas.straetmans@usgictprofessionals.be", thomas.getEmail());
    }

    @Test
    public void testGetCC() throws Exception {
        DummyDB db = DummyDB.getInstance();
        assertEquals("DevCC", db.getCC(new EID("12345abcde6789ts")));
    }

    @Test
    public void testGetAllCCs() throws Exception {
        DummyDB db = DummyDB.getInstance();
        assertTrue(db.getAllCCs().size() > 0);
    }

    @Test
    public void testGetMembers() throws Exception {
        DummyDB db = DummyDB.getInstance();
        assertEquals(2, db.getMembers("DevCC").size());
    }
}