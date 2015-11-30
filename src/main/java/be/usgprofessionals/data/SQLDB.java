package be.usgprofessionals.data;

import be.usgprofessionals.POJOs.AdvancedUserProfile;
import be.usgprofessionals.POJOs.BasicUserProfile;
import be.usgprofessionals.POJOs.CC;
import be.usgprofessionals.Utils.EID;

import java.util.ArrayList;
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


    @Override
    public BasicUserProfile getBasicUser(EID id){

        return null;
    }

    @Override
    public AdvancedUserProfile getAdvancedUser(EID id) {
        return null;
    }

    @Override
    public String getCC(EID id) {
        return null;
    }

    @Override
    public ArrayList<CC> getAllCCs() {
        return null;
    }

    @Override
    public ArrayList<EID> getMembers(String cc) {
        return null;
    }
}
