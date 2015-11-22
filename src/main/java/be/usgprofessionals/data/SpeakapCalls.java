package be.usgprofessionals.data;

import be.usgprofessionals.Utils.EID;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Pantera on 20/11/15.
 */
public class SpeakapCalls {

    private static SpeakapCalls uniqueInstance;
    private static final String networkEID = "";
    private static final String accessToken = "";

    private SpeakapCalls(){}

    public static SpeakapCalls getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new SpeakapCalls();
        }
        return uniqueInstance;
    }

    public HashMap<String, Object> getBasicUserInfo(EID eid) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.speakap.io/networks/"+networkEID+"/users/"+eid,
                String.class);

        HashMap<String,Object> result =
                new ObjectMapper().readValue(response.getBody(), HashMap.class);
        return result;
    }

    public HashMap<String, Object> getAdvancedUserInfo(EID eid){
        return null;
    }
}
