package be.usgprofessionals.POJOs;

import be.usgprofessionals.Utils.EID;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
public class AdvancedUserProfile extends BasicUserProfile {

    private ArrayList<Employer> pastEmployers;
    private ArrayList<Skill> skills;
    private String birthday;
    private String email;
    private String tel;

    public AdvancedUserProfile(EID userId, String firstName, String lastName, String email){
        super(userId, firstName, lastName);
        this.email = email;
        pastEmployers = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public AdvancedUserProfile(BasicUserProfile basic){
        super(basic.getUserId(), basic.getFirstName(), basic.getLastName());
        setProfilePicURI(basic.getProfilePicURI());
        setEmployer(basic.getEmployer());
        setUniqueProperty(basic.getUniqueProperty());
        setProject(basic.getProject());
        setIntern(basic.isIntern());
        setCc(basic.getCc());
        setReportsTo(basic.getReportsTo());
        pastEmployers = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public ArrayList<Employer> getPastEmployers() {
        return pastEmployers;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public void addPastEmployers(Employer pastEmployer) {
        this.pastEmployers.add(pastEmployer);
    }

    public void addSkills(Skill skill) {
        this.skills.add(skill);
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
