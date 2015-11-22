package be.usgprofessionals.POJOs;

/**
 * Created by Pantera on 17/11/15.
 */
public class Employer {

    private String name;
    private String adress;
    private String city;

    public Employer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public void setLocation(String adress, String city){
        this.adress = adress;
        this.city = city;
    }
}
