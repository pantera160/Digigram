package be.usgprofessionals.data;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.CC;
import be.usgprofessionals.POJOs.Employer;
import be.usgprofessionals.Utils.EID;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 20/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public interface Database {

    BasicUserProfile getBasicUser(EID id) throws EIDFormatIncorrectException;

    AdvancedUserProfile getAdvancedUser(EID id) throws EIDFormatIncorrectException;

    String getCC(EID id);

    ArrayList<CC> getAllCCs() throws EIDFormatIncorrectException;

    ArrayList<EID> getMembers(String cc) throws EIDFormatIncorrectException;

    BasicUserProfile getDeptManager(String dept) throws EIDFormatIncorrectException;

    HashMap<EID, BasicUserProfile> getDeptMembers(String dept);

    Employer getEmployer(String employername);

    HashMap<EID, BasicUserProfile> getCompanyEmployees(String companyname);
}
