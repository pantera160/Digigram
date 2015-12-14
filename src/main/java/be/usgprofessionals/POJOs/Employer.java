package be.usgprofessionals.POJOs;

/**
 * Created by Thomas Straetmans on 17/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class Employer {

    private String name;
    private String adress;
    private String city;

    public Employer(String name) {
        this.name = name;
    }

    public Employer(String name, String city, String adress) {
        this.name = name;
        this.city = city;
        this.adress = adress;
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

    public void setLocation(String adress, String city) {
        this.adress = adress;
        this.city = city;
    }
}
