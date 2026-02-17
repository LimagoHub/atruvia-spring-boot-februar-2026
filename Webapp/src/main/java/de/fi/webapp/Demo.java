package de.fi.webapp;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonRepository personRepository;


    @PostConstruct
    public void postConstruct(){
       /* PersonEntity person = PersonEntity.builder().vorname("John").nachname("Doe").id(UUID.randomUUID()).build();
        personRepository.save(person);

        */

        personRepository.xyz().forEach(System.out::println);
    }
}
