package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonService personenServiceMock;

    //@Sql({"/create.sql", "/other.sql"})
    @Test
    void findByIdTest() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        //   when(personenServiceMock.findeAnhandId(any())).thenReturn(optionalPerson);
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var personDto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        assertEquals("John", personDto.getVorname());
    }

    @Test
    void test2() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var s = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(s);
    }

    @Test
    void test3() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);


        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        var personDto =entity.getBody();
        assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void test4() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.empty();

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        //var personDto =entity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

    @Test
    void test5() throws Exception{

        var personDto = PersonDTO.builder().id(UUID.randomUUID()).vorname("Max").nachname("Mustermann").build();

        HttpEntity<PersonDTO> httpEntity = new HttpEntity<>(personDto);

        var personen = List.of(
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Rambo").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Wayne").build()
        );
        when(personenServiceMock.findeAlle()).thenReturn(personen);



        var entity = restTemplate.exchange("/v1/personen", HttpMethod.GET, httpEntity,  new ParameterizedTypeReference<List<PersonDTO>>() { });
        assertEquals(3, entity.getBody().size());

        //verify(personenServiceMock, times(1)).findeAnhandId(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"));
    }


}