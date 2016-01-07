package be.usgprofessionals.restresources;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.Employer;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.controllers.DataDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 17/11/15.
 * <p>
 * Digigram for USG Professionals
 */
@RestController
@RequestMapping("/data")
public class RESTDataService {

    public RESTDataService() {

    }

    @RequestMapping("/basic/{EID}")
    public BasicUserProfile getBasicUser(@PathVariable String EID) {
        try {
            EID eid = new EID(EID);
            return DataDAO.getInstance().getBasicFromEID(eid);
        } catch (Exception e) {
            e.printStackTrace();
            return new BasicUserProfile(null, null, null);
        }
    }

    @RequestMapping("/advanced/{EID}")
    public AdvancedUserProfile getAdvancedUser(@PathVariable String EID) {
        try {
            EID eid = new EID(EID);
            return DataDAO.getInstance().getAdvancedFromEID(eid);
        } catch (Exception e) {
            e.printStackTrace();
            return new AdvancedUserProfile(null, null, null, null);
        }
    }

    @RequestMapping("/deptmanager/{dept}")
    public BasicUserProfile getDeptManager(@PathVariable String dept) {
        try {
            return DataDAO.getInstance().getDeptManager(dept);
        } catch (EIDFormatIncorrectException e) {
            e.printStackTrace();
            return new BasicUserProfile(null, null, null);
        }
    }

    @RequestMapping(value = "/ccmembers/{cc}", produces = {"application/json"})
    public HashMap<EID, BasicUserProfile> getMembers(@PathVariable("cc") String cc) {
        try {
            return DataDAO.getInstance().getDeptMembers(cc);
        } catch (EIDFormatIncorrectException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getCCs() {
        return null;
    }

    public ArrayList<String> getAfdelingen() {
        return null;
    }

    @RequestMapping("/test")
    public HashMap<String, String> test() {
        HashMap<String, String> testmap = new HashMap<>();
        testmap.put("what?", "a test");
        testmap.put("seems", "it worked");
        return testmap;
    }

    @RequestMapping("/company/{employername}")
    public Employer getEmployer(@PathVariable("employername") String employername) {
        return DataDAO.getInstance().getEmployer(employername);
    }

    @RequestMapping("/companyemployees/{companyname}")
    public HashMap<EID, BasicUserProfile> getCompanyEmployees(@PathVariable("companyname") String companyname) {
        try {
            return DataDAO.getInstance().getCompanyEmployees(companyname);
        } catch (EIDFormatIncorrectException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/search/{searchstring}")
    public ArrayList<BasicUserProfile> search(@PathVariable("searchstring") String search) {
        return DataDAO.getInstance().search(search);
    }

    @RequestMapping("/search/")
    public ArrayList<BasicUserProfile> searchNullValue() {
        return null;
    }

}
