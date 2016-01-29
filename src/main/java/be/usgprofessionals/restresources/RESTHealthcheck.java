package be.usgprofessionals.restresources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thomas Straetmans on 29/01/2016.
 * <p>
 * Digigram for USG Professionals
 */

@RestController
@RequestMapping("/")
public class RESTHealthcheck {

    @RequestMapping("")
    public boolean healthCheck() {
        return true;
    }
}
