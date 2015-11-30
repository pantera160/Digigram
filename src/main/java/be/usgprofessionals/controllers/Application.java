package be.usgprofessionals.controllers;

import be.usgprofessionals.restresources.RESTDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Thomas Straetmans on 27/11/15.
 * <p>
 * Digigram for USG Professionals
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = RESTDataService.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
