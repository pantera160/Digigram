package be.usgprofessionals.data;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.CC;
import be.usgprofessionals.Utils.EID;

import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 20/11/15.
 *
 * Digigram for USG Professionals
 */
public interface Database {

    public BasicUserProfile getBasicUser(EID id);
    public AdvancedUserProfile getAdvancedUser(EID id);
    public String getCC(EID id);
    public ArrayList<CC> getAllCCs();
    public ArrayList<EID> getMembers(String cc);

}
