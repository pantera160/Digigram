package be.usgprofessionals.controllers;

import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.data.SpeakapCalls;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Pantera on 20/11/15.
 */
public class DataDAO {

    private static DataDAO uniqueInstance;

    private DataDAO(){
    }

    public static DataDAO getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new DataDAO();
        }
        return uniqueInstance;
    }

    public BasicUserProfile getBasicFromEID(EID id) throws IOException {
        HashMap<String, Object> result = SpeakapCalls.getInstance().getBasicUserInfo(id);
        return null;
    }
}
