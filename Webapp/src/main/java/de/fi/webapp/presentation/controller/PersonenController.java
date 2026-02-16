package de.fi.webapp.presentation.controller;


import de.fi.webapp.presentation.dto.PersonDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> getPerson(@PathVariable UUID id) {

        if(id.toString().endsWith("1")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> getPersons(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt") String nachname
    ) {

        System.out.println(vorname + " " + nachname);
        var list = List.of(
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build(),
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John").nachname("Rambo").build(),
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John").nachname("Wick").build(),
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John").nachname("McClain").build(),
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John Boy").nachname("Walton").build()

        );

        return ResponseEntity.ok(list);
    }
    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        if(id.toString().endsWith("1")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save (@Valid @RequestBody  PersonDTO personDTO,  UriComponentsBuilder uriBuilder ) {

        // Speichern der Person
        personDTO.setId(UUID.randomUUID());
        UriComponents uriComponents = uriBuilder.path("/personen/{id}").buildAndExpand(personDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update (@PathVariable UUID id,@Valid @RequestBody  PersonDTO personDTO ) {

        // Speichern der Person
        if(! id.equals(personDTO.getId())) {
            throw new IllegalArgumentException("upps");
        }

        return ResponseEntity.ok().build();
    }
}
