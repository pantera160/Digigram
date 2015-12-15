package be.usgprofessionals.data;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.CC;
import be.usgprofessionals.Utils.EID;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 14/12/2015.
 * <p>
 * Digigram for USG Professionals
 */
public class SQLDBTest {

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testGetBasicUserUserGetsFound() throws Exception {
        BasicUserProfile profile = SQLDB.getInstance().getBasicUser(new EID("12345abcde6789tl"));
        assertTrue(!(profile == null));
        assertEquals("Tom", profile.getFirstName());
    }

    @Test
    public void testGetAdvancedUser() throws Exception {
        AdvancedUserProfile profile = SQLDB.getInstance().getAdvancedUser(new EID("12345abcde6789tl"));
        assertTrue(!(profile == null));
        assertEquals("tom.lecluyse@usgict.be", profile.getEmail());
    }

    @Test
    public void testGetCC() throws Exception {
        String cc = SQLDB.getInstance().getCC(new EID("12345abcde6789tl"));
        assertEquals("management", cc);
    }

    @Test
    public void testGetAllCCs() throws Exception {
        ArrayList<CC> ccs = SQLDB.getInstance().getAllCCs();
        assertTrue(ccs.size() > 0);
    }

    @Test
    public void testGetMembers() throws Exception {
        ArrayList<EID> members = SQLDB.getInstance().getMembers("ICT");
        assertTrue(members.size() > 0);
    }

    @Test
    public void testGetDeptManager() throws Exception {
        BasicUserProfile tom = SQLDB.getInstance().getDeptManager("ICT");
        assertEquals("Tom", tom.getFirstName());
        assertEquals("management", tom.getDept().getName());
    }

    @Test
    public void testGetDeptMembers() throws Exception {
        HashMap<EID, BasicUserProfile> map = SQLDB.getInstance().getDeptMembers("ICT");
        assertTrue(map.entrySet().size() > 0);
    }
}