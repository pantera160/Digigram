package be.usgprofessionals.data;

import be.usgprofessionals.Utils.EID;

import java.util.HashMap;

/**
 * Created by Thomas Straetmans on 20/11/15.
 *
 * Digigram for USG Professionals
 */
public class SQLDB implements Database {

    private static SQLDB uniqueInstance;

    private SQLDB(){}

    public static SQLDB getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new SQLDB();
        }
        return uniqueInstance;
    }


    //Queries will be called using querydsl


    public HashMap<String, Object> getBasicUser(EID id){

        return null;
    }

    public HashMap<String, Object> getAdvancedUser(EID id){
        return null;
    }

}
