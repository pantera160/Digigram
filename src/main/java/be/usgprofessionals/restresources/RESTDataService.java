package be.usgprofessionals.restresources;

import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.controllers.DataDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pantera on 17/11/15.
 */
@RestController
public class RESTDataService {

    public RESTDataService() {

    }

    @RequestMapping("/basic/{EID}")
    public BasicUserProfile getBasicUser(@PathVariable("EID") EID eid){
        try {
            return DataDAO.getInstance().getBasicFromEID(eid);
        } catch(Exception e){
            e.printStackTrace();
            return new BasicUserProfile(null, null, null);
        }
    }


}
