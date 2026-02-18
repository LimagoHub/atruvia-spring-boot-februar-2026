package de.fi.webapp;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.repository.PersonRepository;
import de.fi.webapp.service.MailConfigService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    //private final PersonRepository personRepository;

    private final MailConfigService mailConfigService;

    @PostConstruct
    public void postConstruct(){

        mailConfigService.send("Testmail", "Hallo");
    }
}
