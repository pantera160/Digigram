package be.usgprofessionals.data;

/**
 * Created by Pantera on 20/11/15.
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


}
