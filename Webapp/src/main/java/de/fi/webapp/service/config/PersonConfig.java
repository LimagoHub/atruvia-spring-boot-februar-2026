package de.fi.webapp.service.config;


import de.fi.webapp.persistence.repository.PersonRepository;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.internal.PersonServiceImpl;
import de.fi.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

/*

    Erstens: wenn kein Zugriff auf den Quellcode besteht
    Zweitens: Erzeugen des Objektes ist komplex
    Drittens: Technische Annotationen sind nicht erwuenscht

 */



@Configuration
public class PersonConfig {


    @Bean
    @Qualifier("antipathen")
    public List<String> createAntipathen(){
        return List.of("Attila","Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> createFruits(){
        return List.of("Cherry","Apple","Strawberry","Banana");
    }

    /*
    @Bean
    public PersonService createPersoneService(final PersonRepository repo, final PersonMapper mapper, @Qualifier("antipathen") final List<String> antipathen) {
        return new PersonServiceImpl(repo, mapper, antipathen);
    }
    */
    /*
    @Bean
    public Set<String> createSet(){
        return Set.of("A","B","C");
    }

     */
}
