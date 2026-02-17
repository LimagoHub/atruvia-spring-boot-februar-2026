package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.repository.PersonRepository;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class,propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /*
                person == null -> PSE
                vorname == null oder zu kurz ->PSE
                nachname dito

                Attila -> PSE

                exception in underlying service ->PSE

                happy day -> Person to Repo

             */



    @Override
    public boolean speichern(final Person person) throws PersonenServiceException {


        try {
            if(person == null)
                throw new PersonenServiceException("Parameter darf nicht null sein");

            if(personRepository.existsById(person.getId())) return false;

            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw new PersonenServiceException("Vorname zu kurz");

            if(person.getNachname() == null || person.getNachname().length() < 2)
                throw new PersonenServiceException("Nachname zu kurz");

            if(person.getVorname().equals("Attila"))
                throw new PersonenServiceException("Antipath!");

            personRepository.save(personMapper.convert(person));

            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }



    @Override
    public boolean aendern(final Person person) throws PersonenServiceException {
        try {
            if(person == null)
                throw new PersonenServiceException("Parameter darf nicht null sein");

            if(! personRepository.existsById(person.getId())) return false;

            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw new PersonenServiceException("Vorname zu kurz");

            if(person.getNachname() == null || person.getNachname().length() < 2)
                throw new PersonenServiceException("Nachname zu kurz");

            if(person.getVorname().equals("Attila"))
                throw new PersonenServiceException("Antipath!");

            personRepository.save(personMapper.convert(person));

            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersonenServiceException{
        try {
            return personRepository.findById(id).map(personMapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim speichern", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException{
        try {
            return personMapper.convert(personRepository.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim speichern", e);
        }
    }

    @Override
    public boolean loesche(final UUID id) throws PersonenServiceException {
        try {
            if (! personRepository.existsById(id)) return false;
            personRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim speichern", e);
        }
    }

    private void saveOrUpdate(final Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Parameter darf nicht null sein");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname zu kurz");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonenServiceException("Nachname zu kurz");

        if(person.getVorname().equals("Attila"))
            throw new PersonenServiceException("Antipath!");

        personRepository.save(personMapper.convert(person));
    }
}
