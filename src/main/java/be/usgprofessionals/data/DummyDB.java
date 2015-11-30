package be.usgprofessionals.data;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.*;
import be.usgprofessionals.Utils.EID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class DummyDB implements Database {

    private static DummyDB uniqueInstance;
    private final HashMap<String, HashMap<String, Object>> database;

    private DummyDB(){

        database = new HashMap<>();
        try {
            fillDB(database);
        } catch (EIDFormatIncorrectException e) {
            e.printStackTrace();
        }
    }

    public static DummyDB getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new DummyDB();
        }
        return uniqueInstance;
    }

    @Override
    public BasicUserProfile getBasicUser(EID id) {
        HashMap<String, Object> user = database.get(id.getId());
        BasicUserProfile profile = new BasicUserProfile(id, user.get("firstname").toString(), user.get("lastname").toString());
        profile.setProject((Project) user.get("project"));
        profile.setProfilePicURI(user.get("profilePicURI").toString());
        profile.setEmployer((Employer) user.get("employer"));
        profile.setIntern((Boolean) user.get("intern"));
        profile.setUniqueProperty(user.get("uniqueProperty").toString());
        return profile;
    }

    @Override
    public AdvancedUserProfile getAdvancedUser(EID id) {
        HashMap<String, Object> user = database.get(id.getId());
        AdvancedUserProfile profile = new AdvancedUserProfile(id, user.get("firstname").toString(), user.get("lastname").toString(), user.get("email").toString());
        profile.setTel(user.get("tel").toString());
        profile.setProject((Project) user.get("project"));
        profile.setProfilePicURI(user.get("profilePicURI").toString());
        profile.setEmployer((Employer) user.get("employer"));
        profile.setIntern((Boolean) user.get("intern"));
        profile.setUniqueProperty(user.get("uniqueProperty").toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(user.get("birthday").toString(), formatter);
        profile.setBirthday(date);
        ((ArrayList<Employer>) user.get("pastemployers")).forEach(profile::addPastEmployers);
        ((ArrayList<Skill>)user.get("skills")).forEach(profile::addSkills);
        return profile;
    }

    @Override
    public String getCC(EID id) {
        return database.get(id.getId()).get("CC").toString();
    }

    @Override
    public ArrayList<CC> getAllCCs() {
        ArrayList<CC> cclist = new ArrayList<>();
        HashMap<String, Object> ccs = database.get("ccs");
        ccs.forEach((k, v) -> cclist.add((CC) v));
        return cclist;
    }

    @Override
    public ArrayList<EID> getMembers(String cc) {
        ArrayList<EID> members = new ArrayList<>();
        try {
            members.add(new EID("12345abcde6789nh"));
            members.add(new EID("12345abcde6789ts"));
        } catch (EIDFormatIncorrectException e) {
            e.printStackTrace();
        }
        return members;
    }

    private void fillDB(HashMap<String, HashMap<String, Object>> db) throws EIDFormatIncorrectException {
        //Create user 1 (Thomas Straetmans)
        HashMap<String, Object> user1 = new HashMap<>();
        user1.put("firstname", "Thomas");
        user1.put("lastname", "Straetmans");
        user1.put("profilePicURI", "thomasstraetmans.png");
        user1.put("uniqueProperty", "fun");
        user1.put("intern", true);
        Project project = new Project("Digigram");
        project.addSkill(new Skill("Java", "Development"));
        project.addSkill(new Skill("AngularJS", "Development"));
        project.addSkill(new Skill("Spring", "Development"));
        user1.put("project", project);
        Employer employer = new Employer("USG Professionals");
        user1.put("employer", employer);
        ArrayList<Employer> employers = new ArrayList<Employer>();
        employers.add(employer);
        user1.put("pastemployers", employers);
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(new Skill("Java", "Development"));
        user1.put("skills", skills);
        user1.put("email", "thomas.straetmans@usgictprofessionals.be");
        user1.put("tel", "0479/785694");
        user1.put("birthday", "22/12/1991");
        user1.put("reportsTo", "12345abcde6789nh");
        user1.put("CC", new CC("DevCC", new EID("12345abcde6789nh")));
        db.put("12345abcde6789ts", user1);

        //Create CC overview
        HashMap<String, Object> ccs = new HashMap<>();
        CC cc = new CC("DevCC", new EID("12345abcde6789nh"));
        ccs.put("1", cc);
        cc = new CC("BICC", new EID("12345abcde6789gs"));
        ccs.put("2", cc);
        cc = new CC("InfraCC", new EID("12345abcde6789rk"));
        ccs.put("3", cc);
        db.put("ccs", ccs);
    }
}
