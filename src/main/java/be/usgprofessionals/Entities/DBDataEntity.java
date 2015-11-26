package be.usgprofessionals.Entities;

import be.usgprofessionals.POJOs.Employer;
import be.usgprofessionals.POJOs.Project;
import be.usgprofessionals.Utils.EID;

import javax.persistence.Entity;

/**
 * Created by Thomas Straetmans on 23/11/15.
 * <p>
 * Digigram for USG Professionals
 */
@Entity
public class DBDataEntity {

    private EID id;
    private boolean intern;
    private Employer employer;
    private Project project;
    private String property;

    public DBDataEntity(){

    }

    public EID getId() {
        return id;
    }

    public void setId(EID id) {
        this.id = id;
    }

    public boolean isIntern() {
        return intern;
    }

    public void setIntern(boolean intern) {
        this.intern = intern;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer empoyer) {
        this.employer = empoyer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
