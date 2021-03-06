package be.usgprofessionals.POJOs;


import be.usgprofessionals.Utils.EID;

/**
 * Created by Thomas Straetmans on 17/11/15.
 * <p>
 * Digigram for USG Professionals
 */
public class BasicUserProfile {

    private EID userId;
    private String firstName;
    private String lastName;
    private String profilePicURI;
    private String uniqueProperty;
    private Project project;
    private Employer employer;
    private boolean intern;
    private CC dept;
    private EID reportsTo;
    private String nextDept;

    public BasicUserProfile(EID userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePicURI() {
        return profilePicURI;
    }

    public String getUniqueProperty() {
        return uniqueProperty;
    }

    public Project getProject() {
        return project;
    }

    public Employer getEmployer() {
        return employer;
    }

    public boolean isIntern() {
        return intern;
    }

    public void setProfilePicURI(String profilePicURI) {
        this.profilePicURI = profilePicURI;
    }

    public void setUniqueProperty(String uniqueProperty) {
        this.uniqueProperty = uniqueProperty;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void setIntern(boolean intern) {
        this.intern = intern;
    }

    public CC getDept() {
        return dept;
    }

    public void setDept(CC dept) {
        this.dept = dept;
    }

    public EID getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(EID reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getNextDept() {
        return nextDept;
    }

    public void setNextDept(String nextDept) {
        this.nextDept = nextDept;
    }
}
