package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
//@RequestScope
public class PersonenController {



    public PersonenController() {
        System.out.println( "PersonenController erzeugt");
    }

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



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
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDTO.getId());
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
