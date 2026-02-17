package de.fi.webapp.persistence.repository;

import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.StyledEditorKit;
import java.util.UUID;

public interface PersonRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p.nachname from PersonEntity p")
    Iterable<String> xyz();

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> projection();

    @Query("select new de.fi.webapp.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findTinyPersons();
}
