package be.usgprofessionals.POJOs;

import be.usgprofessionals.Utils.EID;

/**
 * Created by Thomas Straetmans on 27/11/15.
 *
 * Digigram for USG Professionals
 */
public class CC {

    private String name;
    private EID ccManager;

    public CC(String name, EID ccManager){
        this.name = name;
        this.ccManager = ccManager;
    }

    public String getName() {
        return name;
    }

    public EID getCcManager() {
        return ccManager;
    }

    @Override
    public String toString(){
        return name;
    }
}
