package be.usgprofessionals.data;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.*;
import be.usgprofessionals.*;
import be.usgprofessionals.Utils.EID;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Thomas Straetmans on 20/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class SQLDB implements Database {

    private static SQLDB uniqueInstance;
    private static String DBUSER, DBPASS, DBURL;
    private Connection connection;

    private SQLDB() {
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(is);
            DBUSER = prop.get("DBUSER").toString();
            DBPASS = prop.get("DBPASS").toString();
            DBURL = prop.get("DBURL").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SQLDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SQLDB();
        }
        return uniqueInstance;
    }

    //Creating the connection to the db
    private SQLQuery createConnectionQuery() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                e.printStackTrace();
            }
        }
        SQLTemplates dialect = new MySQLTemplates();
        return new SQLQuery(connection, dialect);
    }

    //Queries will be called using querydsl


    @Override
    public BasicUserProfile getBasicUser(EID id) throws EIDFormatIncorrectException {
        QUsers quser = new QUsers("u");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept = new QDepartments("d");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(quser)
                .join(qEmployer).on(qEmployer.employerId.eq(quser.employerId))
                .join(qdept).on(qdept.deptId.eq(quser.deptId))
                .join(qProject).on(qProject.projectId.eq(quser.projectId))
                .leftJoin(qdept2).on(qdept2.deptId.eq(quser.nextDeptId))
                .where(quser.userId.eq(id.getId()))
                .list(quser.userId, quser.firstName, quser.lastName, quser.intern, quser.profilePicURI, quser.uniqueProperty, quser.reportsTo, qEmployer.name, qEmployer.adress,
                        qEmployer.city, qEmployer.iconURI, qdept.deptName, qdept.managerId, qdept2.deptName, qProject.name, qProject.projectId);

        Tuple t = results.get(0);
        return tupleToBasicUser(t);
    }

    @Override
    public AdvancedUserProfile getAdvancedUser(EID id) throws EIDFormatIncorrectException {
        QUsers quser = new QUsers("u");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept = new QDepartments("d");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(quser)
                .join(qEmployer).on(qEmployer.employerId.eq(quser.employerId))
                .join(qdept).on(qdept.deptId.eq(quser.deptId))
                .join(qProject).on(qProject.projectId.eq(quser.projectId))
                .leftJoin(qdept2).on(qdept2.deptId.eq(quser.nextDeptId))
                .where(quser.userId.eq(id.getId()))
                .list(quser.userId, quser.firstName, quser.lastName, quser.intern, quser.profilePicURI, quser.uniqueProperty, quser.reportsTo, quser.email, quser.birthday, quser.tel, qEmployer.name, qEmployer.adress,
                        qEmployer.city, qEmployer.iconURI, qdept.deptName, qdept.managerId, qdept2.deptName, qProject.name, qProject.projectId);
        if (results.size() > 0) {
            Tuple t = results.get(0);

            AdvancedUserProfile advancedUserProfile = new AdvancedUserProfile(tupleToBasicUser(t));
            advancedUserProfile.setBirthday(t.get(quser.birthday).toString());
            advancedUserProfile.setTel(t.get(quser.tel));
            advancedUserProfile.setEmail(t.get(quser.email));
            return advancedUserProfile;
        } else {
            return null;
        }
    }

    @Override
    public String getCC(EID id) {
        SQLQuery query = createConnectionQuery();
        QUsers quser = new QUsers("u");
        QDepartments qdept = new QDepartments("d");
        List<String> result = query
                .from(quser, qdept)
                .where(quser.deptId.eq(qdept.deptId))
                .where(quser.userId.eq(id.getId()))
                .list(qdept.deptName);
        if (result.size() > 0)
            return result.get(0);
        else return null;
    }

    @Override
    public ArrayList<CC> getAllCCs() throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QDepartments qdept = new QDepartments("d");
        List<Tuple> result = query
                .from(qdept)
                .list(qdept.deptName, qdept.managerId);

        ArrayList<CC> list = new ArrayList<>();
        for (Tuple t : result) {
            CC cc = new CC(t.get(qdept.deptName), new EID(t.get(qdept.managerId)));
            list.add(cc);
        }
        return list;
    }

    @Override
    public ArrayList<EID> getMembers(String cc) throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QUsers qusers = new QUsers("u");
        QDepartments qdept = new QDepartments("d");
        List<String> result = query
                .from(qusers, qdept)
                .where(qusers.deptId.eq(qdept.deptId))
                .list(qusers.userId);

        ArrayList<EID> list = new ArrayList<>();
        result.forEach(l -> {
            try {
                list.add(new EID(l));
            } catch (EIDFormatIncorrectException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public BasicUserProfile getDeptManager(String dept) throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QUsers qusers = new QUsers("u");
        QDepartments qdept = new QDepartments("d");
        List<String> result = query
                .from(qusers, qdept)
                .where(qdept.deptName.eq(dept))
                .where(qusers.userId.eq(qdept.managerId))
                .list(qusers.userId);
        if (result.size() > 0) {
            String userid = result.get(0);
            return getBasicUser(new EID(userid));
        } else {
            return null;
        }
    }

    @Override
    public HashMap<EID, BasicUserProfile> getDeptMembers(String dept) throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QUsers quser = new QUsers("u");
        QDepartments qdept = new QDepartments("d");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        List<Tuple> result = query
                .from(quser)
                .join(qEmployer).on(qEmployer.employerId.eq(quser.employerId))
                .join(qdept).on(qdept.deptId.eq(quser.deptId)).on(qdept.deptName.eq(dept))
                .join(qProject).on(qProject.projectId.eq(quser.projectId))
                .leftJoin(qdept2).on(qdept2.deptId.eq(quser.nextDeptId))
                .list(quser.userId, quser.firstName, quser.lastName, quser.intern, quser.profilePicURI, quser.uniqueProperty, quser.reportsTo, qEmployer.name, qEmployer.adress,
                        qEmployer.city, qEmployer.iconURI, qdept.deptName, qdept.managerId, qdept2.deptName, qProject.name, qProject.projectId);

        HashMap<EID, BasicUserProfile> map = new HashMap<>();
        for (Tuple t : result) {
            map.put(new EID(t.get(quser.userId)), tupleToBasicUser(t));
        }

        return map;
    }

    @Override
    public Employer getEmployer(String employername) {
        SQLQuery query = createConnectionQuery();
        QEmployer qEmployer = new QEmployer("e");
        List<Tuple> result = query
                .from(qEmployer)
                .where(qEmployer.name.eq(employername))
                .list(qEmployer.name, qEmployer.adress, qEmployer.city, qEmployer.iconURI);

        if (result.size() > 0) {
            Tuple t = result.get(0);
            return new Employer(t.get(qEmployer.name), t.get(qEmployer.city), t.get(qEmployer.adress), t.get(qEmployer.iconURI));

        } else {
            return null;
        }
    }

    @Override
    public HashMap<EID, BasicUserProfile> getCompanyEmployees(String companyname) throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QUsers quser = new QUsers("u");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept = new QDepartments("d");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        List<Tuple> result = query
                .from(quser)
                .join(qEmployer).on(qEmployer.employerId.eq(quser.employerId)).on(qEmployer.name.eq(companyname))
                .join(qdept).on(qdept.deptId.eq(quser.deptId))
                .join(qProject).on(qProject.projectId.eq(quser.projectId))
                .leftJoin(qdept2).on(qdept2.deptId.eq(quser.nextDeptId))
                .list(quser.userId, quser.firstName, quser.lastName, quser.intern, quser.profilePicURI, quser.uniqueProperty, quser.reportsTo, qEmployer.name, qEmployer.adress,
                        qEmployer.city, qEmployer.iconURI, qdept.deptName, qdept.managerId, qdept2.deptName, qProject.name, qProject.projectId);

        HashMap<EID, BasicUserProfile> map = new HashMap<>();
        for (Tuple t : result) {
            map.put(new EID(t.get(quser.userId)), tupleToBasicUser(t));
        }
        return map;
    }

    @Override
    public ArrayList<BasicUserProfile> search(String searchstring) {
        SQLQuery query = createConnectionQuery();
        QUsers quser = new QUsers("u");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept = new QDepartments("d");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(quser.lastName.like("%" + searchstring + "%"));
        booleanBuilder.or(quser.firstName.like("%" + searchstring + "%"));
        List<Tuple> result = query
                .from(quser)
                .join(qEmployer).on(qEmployer.employerId.eq(quser.employerId))
                .join(qdept).on(qdept.deptId.eq(quser.deptId))
                .join(qProject).on(qProject.projectId.eq(quser.projectId))
                .leftJoin(qdept2).on(qdept2.deptId.eq(quser.nextDeptId))
                .where(booleanBuilder)
                .list(quser.userId, quser.firstName, quser.lastName, quser.intern, quser.profilePicURI, quser.uniqueProperty, quser.reportsTo, qEmployer.name, qEmployer.adress,
                        qEmployer.city, qEmployer.iconURI, qdept.deptName, qdept.managerId, qdept2.deptName, qProject.name, qProject.projectId);

        ArrayList<BasicUserProfile> list = new ArrayList<>();
        result.forEach(t -> {
            try {
                list.add(tupleToBasicUser(t));
            } catch (EIDFormatIncorrectException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    private BasicUserProfile tupleToBasicUser(Tuple t) throws EIDFormatIncorrectException {
        SQLQuery query = createConnectionQuery();
        QUsers quser = new QUsers("u");
        QEmployer qEmployer = new QEmployer("e");
        QDepartments qdept = new QDepartments("d");
        QDepartments qdept2 = new QDepartments("d2");
        QProject qProject = new QProject("p");
        QSkill qSkill = new QSkill("sk");
        QProjectSkills qProjectSkills = new QProjectSkills("qpss");

        BasicUserProfile basicUserProfile = new BasicUserProfile(new EID(t.get(quser.userId)), t.get(quser.firstName), t.get(quser.lastName));
        basicUserProfile.setUniqueProperty(t.get(quser.uniqueProperty));
        basicUserProfile.setProfilePicURI(t.get(quser.profilePicURI));
        Project proj = new Project(t.get(qProject.name));
        basicUserProfile.setProject(proj);
        Employer empl = new Employer(t.get(qEmployer.name), t.get(qEmployer.adress), t.get(qEmployer.city), t.get(qEmployer.iconURI));
        basicUserProfile.setIntern(t.get(quser.intern));
        basicUserProfile.setEmployer(empl);
        basicUserProfile.setDept(new CC(t.get(qdept.deptName), new EID(t.get(qdept.managerId))));
        basicUserProfile.setReportsTo(new EID(t.get(quser.reportsTo)));
        basicUserProfile.setNextDept(t.get(qdept2.deptName));

        List<Tuple> results2 = query
                .from(qSkill, qProjectSkills)
                .where(qProjectSkills.projectId.eq(t.get(qProject.projectId)))
                .where(qSkill.skillId.eq(qProjectSkills.skillId))
                .list(qSkill.name, qSkill.area);

        for (Tuple t2 : results2) {
            basicUserProfile.getProject().addSkill(new Skill(t2.get(qSkill.name), t2.get(qSkill.area)));
        }

        return basicUserProfile;
    }
}
