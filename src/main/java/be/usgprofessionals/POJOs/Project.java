package be.usgprofessionals.POJOs;

import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 17/11/15.
 *
 * Digigram for USG Professionals
 */
public class Project {

    private String name;
    private ArrayList<Skill> context;

    public Project(String name) {
        this.name = name;
        context = new ArrayList<Skill>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Skill> getContext() {
        return context;
    }

    public void addSkill(Skill skill){
        context.add(skill);
    }
}
