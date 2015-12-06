package be.usgprofessionals.data;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.CC;
import be.usgprofessionals.Utils.EID;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 20/11/15.
 *
 * Digigram for USG Professionals
 */
public interface Database {

    BasicUserProfile getBasicUser(EID id);
    AdvancedUserProfile getAdvancedUser(EID id);
    String getCC(EID id);
    ArrayList<CC> getAllCCs();
    ArrayList<EID> getMembers(String cc);
    BasicUserProfile getDeptManager(String dept);
    HashMap<EID,BasicUserProfile> getDeptMembers(String dept);
}
