package be.usgprofessionals.restresources;

import be.usgprofessionals.Exceptions.DAOException;
import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.controllers.DataDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
@RestController
public class RESTDataService {

    public RESTDataService() {

    }

    @RequestMapping("/basic/{EID}")
    public BasicUserProfile getBasicUser(@PathVariable("EID") String id){
        try {
            EID eid = new EID(id);
            return DataDAO.getInstance().getBasicFromEID(eid);
        } catch(Exception e){
            e.printStackTrace();
            return new BasicUserProfile(null, null, null);
        }
    }

    @RequestMapping("/advanced/{EID}")
    public AdvancedUserProfile getAdvancedUser(@PathVariable("EID") String id){
        try {
            EID eid = new EID(id);
            return DataDAO.getInstance().getAdvancedFromEID(eid);
        } catch (IOException | DAOException | EIDFormatIncorrectException e) {
            e.printStackTrace();
            return new AdvancedUserProfile(null, null, null, null);
        }
    }

    public ArrayList<EID> getMembers(String afdeling){

    }

    public ArrayList<EID> getMembers(String cc){

    }

    public ArrayList<String> getCCs(){

    }

    public ArrayList<String> getAfdelingen(){

    }




}
