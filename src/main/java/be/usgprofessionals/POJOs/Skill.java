package be.usgprofessionals.POJOs;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
public class Skill {

    private String name;
    private String area;

    public Skill(String name, String area){
        this.name= name;
        this.area= area;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }
}
