package be.usgprofessionals.controllers;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.Utils.DBTYPES;
import be.usgprofessionals.Utils.DEFAULTS;
import be.usgprofessionals.Utils.EID;
import be.usgprofessionals.data.Database;
import be.usgprofessionals.data.DummyDB;
import be.usgprofessionals.data.SQLDB;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Thomas Straetmans on 20/11/15.
 *
 * Digigram for USG Professionals
 */
public class DataDAO {

    private static DataDAO uniqueInstance;
    private DBTYPES dbtype;
    private Database database;

    private DataDAO(){
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(is);
            dbtype = DBTYPES.fromString(prop.get("dbtype").toString());
        } catch (Exception e) {
            e.printStackTrace();
            dbtype = DEFAULTS.getDbtype();
        }
        switch(dbtype){
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

    public BasicUserProfile getBasicFromEID(EID eid) {
        return database.getBasicUser(eid);
    }

    public AdvancedUserProfile getAdvancedFromEID(EID eid) {
        return database.getAdvancedUser(eid);
    }

    public ArrayList<EID> getMembers(String cc){
        return database.getMembers(cc);
    }
}
