package be.usgprofessionals.controllers;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.Employer;
import be.usgprofessionals.Utils.DBTYPES;
import be.usgprofessionals.Utils.DEFAULTS;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.data.Database;
import be.usgprofessionals.data.DummyDB;
import be.usgprofessionals.data.SQLDB;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Thomas Straetmans on 20/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class DataDAO {

    private static DataDAO uniqueInstance;
    private DBTYPES dbtype;
    private Database database;

    private DataDAO() {
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(is);
            dbtype = DBTYPES.fromString(prop.get("dbtype").toString());
        } catch (Exception e) {
            e.printStackTrace();
            dbtype = DEFAULTS.getDbtype();
        }
        switch (dbtype) {
            case LOCAL:
                database = DummyDB.getInstance();
                break;
            case MYSQL:
                database = SQLDB.getInstance();
                break;
        }
    }

    public static DataDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DataDAO();
        }

        return uniqueInstance;
    }

    public BasicUserProfile getBasicFromEID(EID eid) throws EIDFormatIncorrectException {
        return database.getBasicUser(eid);
    }

    public AdvancedUserProfile getAdvancedFromEID(EID eid) throws EIDFormatIncorrectException {
        return database.getAdvancedUser(eid);
    }

    public ArrayList<EID> getMembers(String cc) throws EIDFormatIncorrectException {
        return database.getMembers(cc);
    }

    public BasicUserProfile getDeptManager(String dept) throws EIDFormatIncorrectException {
        return database.getDeptManager(dept);
    }

    public HashMap<EID, BasicUserProfile> getDeptMembers(String dept) {
        return database.getDeptMembers(dept);
    }

    public Employer getEmployer(String employername) {
        return database.getEmployer(employername);
    }

    public HashMap<EID, BasicUserProfile> getCompanyEmployees(String companyname){
        return database.getCompanyEmployees(companyname);
    }
}
