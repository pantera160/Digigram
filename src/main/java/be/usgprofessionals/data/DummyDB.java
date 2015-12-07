package be.usgprofessionals.data;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.*;
import be.usgprofessionals.Utils.EID;

import javax.el.MethodNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class DummyDB implements Database {

    private static DummyDB uniqueInstance;
    private final HashMap<EID, BasicUserProfile> employees;
    private final HashMap<EID, AdvancedUserProfile> employees_advanced;
    private final HashMap<String, CC> deptsAndCCs;

    private DummyDB(){

        employees = new HashMap<>();
        deptsAndCCs = new HashMap<>();
        employees_advanced = new HashMap<>();
        try {
            fillDB();
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
        return employees.get(id);
    }

    @Override
    public AdvancedUserProfile getAdvancedUser(EID id) {
        return employees_advanced.get(id);
    }

    @Override
    public String getCC(EID id) {
        throw new MethodNotFoundException("Method not supported");
    }

    @Override
    public ArrayList<CC> getAllCCs() {
        ArrayList<CC> cclist = new ArrayList<>();
        deptsAndCCs.forEach((k, v) -> cclist.add((CC) v));
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

    @Override
    public BasicUserProfile getDeptManager(String dept) {
        EID v = deptsAndCCs.get(dept).getCcManager();
        return employees.get(v);
    }

    @Override
    public HashMap<EID, BasicUserProfile> getDeptMembers(String dept) {
        EID manager = getDeptManager(dept).getUserId();
        Map<EID, BasicUserProfile> map = employees.entrySet()
                .stream()
                .filter(e -> e.getValue().getReportsTo().equals(manager))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if(map instanceof HashMap){
            return (HashMap<EID, BasicUserProfile>) map;
        }
        else return null;
    }

    private void fillDB() throws EIDFormatIncorrectException {
        HashMap<EID, BasicUserProfile> db = employees;
        //Create user 1 (Thomas Straetmans)
        BasicUserProfile user1 = new BasicUserProfile(new EID("12345abcde6789ts"), "Thomas", "Straetmans");
        user1.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/15240855ef0c7354/profile-image");
        user1.setUniqueProperty("fun");
        user1.setIntern(true);
        Project project = new Project("Digigram");
        project.addSkill(new Skill("Java", "Development"));
        project.addSkill(new Skill("AngularJS", "Development"));
        project.addSkill(new Skill("Spring", "Development"));
        user1.setProject(project);
        Employer employer = new Employer("USG Professionals");
        user1.setEmployer(employer);
        user1.setReportsTo(new EID("12345abcde6789nh"));
        user1.setCc(new CC("DevCC", new EID("12345abcde6789nh")));
        db.put(new EID("12345abcde6789ts"), user1);
        AdvancedUserProfile user1Adv = new AdvancedUserProfile(user1);
        user1Adv.addSkills(new Skill("Java", "Development"));
        user1Adv.setEmail("thomas.straetmans@usgictprofessionals.be");
        user1Adv.setTel("0479/785694");
        user1Adv.setBirthday("22/12/1991");
        employees_advanced.put(new EID("12345abcde6789ts"), user1Adv);

        //Create user 2 (Tom Lecluyse)
        BasicUserProfile user2 = new BasicUserProfile(new EID("12345abcde6789tl"), "Tom", "Lecluyse");
        user2.setIntern(true);
        user2.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/0f66dcc4e40c7750/profile-image");
        Project proj = new Project("Managing ICT");
        proj.addSkill(new Skill("Management", "Management"));
        proj.addSkill(new Skill("Stress releive", "Management"));
        Employer employer2 = new Employer("USG Professionals");
        user2.setReportsTo(new EID("12345abcde6789nc"));
        user2.setEmployer(employer2);
        user2.setProject(proj);
        user2.setCc(new CC("ICT", new EID("12345abcde6789tl")));
        user2.setNextDept("ICT");
        db.put(new EID("12345abcde6789tl"), user2);
        AdvancedUserProfile user2Adv = new AdvancedUserProfile(user2);
        user2Adv.setEmail("tom.lecluyse@usgict.be");
        user2Adv.setTel("+32 (0)478/94 56 12");
        user2Adv.setBirthday("");
        employees_advanced.put(new EID("12345abcde6789tl"), user2Adv);

        //Create user 3 (Nadine Hermans)
        BasicUserProfile user3 = new BasicUserProfile(new EID("12345abcde6789nh"), "Nadine", "Hermans");
        user3.setIntern(false);
        user3.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/0f3d79d7ac0c7e94/profile-image");
        Project proj2 = new Project("Managing DevCC");
        proj2.addSkill(new Skill("Management", "Management"));
        proj2.addSkill(new Skill("Stress releive", "Management"));
        Employer employer3 = new Employer("USG Professionals");
        user3.setReportsTo(new EID("12345abcde6789tl"));
        user3.setEmployer(employer3);
        user3.setProject(proj);
        user3.setNextDept("DevCC");
        user3.setCc(new CC("ICT", new EID("12345abcde6789tl")));
        db.put(new EID("12345abcde6789nh"), user3);
        AdvancedUserProfile user3Adv = new AdvancedUserProfile(user3);
        user3Adv.setEmail("nadine.hermans@usgict.be");
        user3Adv.setTel("+32 (0)476/72 00 22");
        user3Adv.setBirthday("20/04/1978");
        employees_advanced.put(new EID("12345abcde6789nh"), user3Adv);

        //Create user 4 (Guy van der Sande)
        BasicUserProfile user4 = new BasicUserProfile(new EID("12345abcde6789gs"), "Guy", "Van der Sande");
        user4.setIntern(true);
        user4.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/0f6a6da2020c7f48/profile-image");
        Project proj4 = new Project("BICC");
        proj4.addSkill(new Skill("Management", "Management"));
        proj4.addSkill(new Skill("Stress releive", "Management"));
        proj4.addSkill(new Skill("BI skills", "BI"));
        Employer employer4 = new Employer("USG Professionals");
        user4.setReportsTo(new EID("12345abcde6789tl"));
        user4.setEmployer(employer4);
        user4.setProject(proj);
        user4.setCc(new CC("ICT", new EID("12345abcde6789tl")));
        db.put(new EID("12345abcde6789gs"), user4);
        AdvancedUserProfile user4Adv = new AdvancedUserProfile(user4);
        user4Adv.setEmail("guy.vandersande@ext.usgict.be");
        user4Adv.setTel("+32 (0)476/50 16 26");
        user4Adv.setBirthday("27/08/1970");
        employees_advanced.put(new EID("12345abcde6789gs"), user4Adv);

        //Create user 5 (Raf De Keersmaecker)
        BasicUserProfile user5 = new BasicUserProfile(new EID("12345abcde6789rk"), "Raf", "De Keersmaecker");
        user5.setIntern(false);
        user5.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/0f66d5cc090c766c/profile-image");
        Project proj5 = new Project("Managing Infra CC");
        proj5.addSkill(new Skill("Management", "Management"));
        proj5.addSkill(new Skill("Stress releive", "Management"));
        proj5.addSkill(new Skill("Infra skills", "Infra"));
        Employer employer5 = new Employer("USG Professionals");
        user5.setReportsTo(new EID("12345abcde6789tl"));
        user5.setEmployer(employer5);
        user5.setProject(proj5);
        user5.setCc(new CC("ICT", new EID("12345abcde6789tl")));
        db.put(new EID("12345abcde6789rk"), user5);
        AdvancedUserProfile user5Adv = new AdvancedUserProfile(user5);
        user5Adv.setEmail("raf.dekeersmaecker@usgict.be");
        user5Adv.setTel("");
        user5Adv.setBirthday("");
        employees_advanced.put(new EID("12345abcde6789rk"), user5Adv);

        //Create user 6 (Jean-François Leys)
        BasicUserProfile user6 = new BasicUserProfile(new EID("12345abcde6789fl"), "Jean-François", "Leys");
        user6.setIntern(false);
        user6.setProfilePicURI("https://usgprofessionalsbe.speakap.com/files/0fae2476080c7a78/profile-image");
        Project proj6 = new Project("Managing BACC");
        proj6.addSkill(new Skill("Management", "Management"));
        proj6.addSkill(new Skill("Stress releive", "Management"));
        proj6.addSkill(new Skill("BA skills", "BA"));
        Employer employer6 = new Employer("USG Professionals");
        user6.setReportsTo(new EID("12345abcde6789tl"));
        user6.setEmployer(employer5);
        user6.setProject(proj5);
        user6.setCc(new CC("ICT", new EID("12345abcde6789tl")));
        db.put(new EID("12345abcde6789fl"), user6);
        AdvancedUserProfile user6Adv = new AdvancedUserProfile(user6);
        user6Adv.setEmail("jean-francois.leys@ext.usgict.be");
        user6Adv.setTel("");
        user6Adv.setBirthday("");
        employees_advanced.put(new EID("12345abcde6789fl"), user6Adv);

        //Create CC overview
        CC cc = new CC("DevCC", new EID("12345abcde6789nh"));
        deptsAndCCs.put(cc.getName(), cc);
        cc = new CC("BICC", new EID("12345abcde6789gs"));
        deptsAndCCs.put(cc.getName(), cc);
        cc = new CC("InfraCC", new EID("12345abcde6789rk"));
        deptsAndCCs.put(cc.getName(), cc);
        cc = new CC("ICT", new EID("12345abcde6789tl"));
        deptsAndCCs.put(cc.getName(), cc);
        cc = new CC("BACC", new EID("12345abcde6789fl"));
        deptsAndCCs.put(cc.getName(), cc);

    }
}
