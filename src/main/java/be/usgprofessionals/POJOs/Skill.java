package be.usgprofessionals.POJOs;

/**
 * Created by Pantera on 17/11/15.
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
