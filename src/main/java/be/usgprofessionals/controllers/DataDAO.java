package be.usgprofessionals.controllers;

import be.usgprofessionals.Exceptions.DAOException;
import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.*;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.data.SQLDB;
import be.usgprofessionals.data.SpeakapCalls;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Thomas Straetmans on 20/11/15.
 *
 * Digigram for USG Professionals
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

    public BasicUserProfile getBasicFromEID(EID id) throws IOException, EIDFormatIncorrectException, DAOException {
        HashMap<String, Object> result = SpeakapCalls.getInstance().getUserInfo(id);
        EID idcheck = new EID(result.get("EID").toString());
        if(idcheck.equals(id)) {
            HashMap<String, Object> dbresult = SQLDB.getInstance().getBasicUser(id);
            String firstname = ((HashMap) result.get("name")).get("firstName").toString();
            String lastname = ((HashMap) result.get("name")).get("familyName").toString();
            BasicUserProfile user = new BasicUserProfile(id, firstname, lastname);
            user.setProfilePicURI(result.get("avatarThumbnailUrl").toString());
            user.setIntern((boolean) dbresult.get("intern"));
            user.setEmployer((Employer) dbresult.get("employer"));
            user.setProject((Project) dbresult.get("project"));
            user.setUniqueProperty(dbresult.get("property").toString());
            return user;
        }
        else{
            throw new DAOException("Returned user and requested user do not have same EID.");
        }
    }

    @SuppressWarnings("unchecked")
    public AdvancedUserProfile getAdvancedFromEID(EID id) throws IOException, DAOException, EIDFormatIncorrectException {
        HashMap<String, Object> result = SpeakapCalls.getInstance().getUserInfo(id);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        EID idcheck = new EID(result.get("EID").toString());
        if(idcheck.equals(id)){
            HashMap<String, Object> dbresult = SQLDB.getInstance().getAdvancedUser(id);
            String firstname = ((HashMap) result.get("name")).get("firstName").toString();
            String lastname = ((HashMap) result.get("name")).get("familyName").toString();
            String email = getWorkEmail(result);
            AdvancedUserProfile user = new AdvancedUserProfile(id, firstname, lastname, email);
            user.setProject((Project) dbresult.get("project"));
            user.setUniqueProperty(dbresult.get("property").toString());
            user.setEmployer((Employer) dbresult.get("employer"));
            user.setIntern((boolean) dbresult.get("intern"));
            user.setProfilePicURI(result.get("avatarTumbnailUrl").toString());
            user.setBirthday(LocalDate.parse(result.get("birthday").toString(), format));
            user.setTel(((ArrayList<HashMap<String, String>>) result.get("telephoneNumbers")).get(0).get("value"));
            ArrayList<Skill> skills = (ArrayList<Skill>) dbresult.get("skills");
            skills.forEach(user::addSkills);
            ArrayList<Employer> pastemployers = (ArrayList<Employer>) dbresult.get("pastemployers");
            pastemployers.forEach(user::addPastEmployers);
            return user;
        }
        else{
            throw new DAOException("Returned user and requested user do not have same EID!");
        }
    }

    @SuppressWarnings("unchecked")
    private String getWorkEmail(HashMap<String, Object> map){
        ArrayList<HashMap<String, String>> emails = (ArrayList<HashMap<String, String>>) map.get("emailAdresses");
        Optional<HashMap<String, String>> email = emails.stream()
                .filter(mail -> mail.get("label").equals("Work"))
                .findFirst();
        return email.get().get("value");
    }
}
