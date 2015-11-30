package be.usgprofessionals.restresources;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.controllers.DataDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
@RestController
@RequestMapping("/data")
public class RESTDataService {

    public RESTDataService() {

    }

    @RequestMapping("/basic/{EID}")
    public BasicUserProfile getBasicUser(@PathVariable String EID){
        try {
            EID eid = new EID(EID);
            return DataDAO.getInstance().getBasicFromEID(eid);
        } catch(Exception e){
            e.printStackTrace();
            return new BasicUserProfile(null, null, null);
        }
    }

    @RequestMapping("/advanced/{EID}")
    public AdvancedUserProfile getAdvancedUser(@PathVariable String EID){
        try {
            EID eid = new EID(EID);
            return DataDAO.getInstance().getAdvancedFromEID(eid);
        } catch (Exception e) {
            e.printStackTrace();
            return new AdvancedUserProfile(null, null, null, null);
        }
    }

    @RequestMapping("/ccmembers/{cc}")
    public ArrayList<EID> getMembers(@PathVariable("cc") String cc) {
        return DataDAO.getInstance().getMembers(cc);
    }

    public ArrayList<String> getCCs(){
        return null;
    }

    public ArrayList<String> getAfdelingen(){
        return null;
    }

    @RequestMapping("/test")
    public HashMap<String, String> test(){
        HashMap<String, String> testmap = new HashMap<>();
        testmap.put("what?", "a test");
        testmap.put("seems", "it worked");
        return testmap;
    }




}
