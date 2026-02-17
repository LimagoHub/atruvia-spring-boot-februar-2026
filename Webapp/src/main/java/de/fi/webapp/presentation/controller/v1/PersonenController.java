package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.presentation.mapper.PersonDtoMapper;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PersonenController {

    private final PersonService personService;
    private final PersonDtoMapper mapper;

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
    public ResponseEntity<PersonDTO> getPerson(@PathVariable UUID id) throws PersonenServiceException{


        // of nur bei Optionalem Wert
        return ResponseEntity.of(personService.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> getPersons(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt") String nachname
    ) throws PersonenServiceException{

        System.out.println(vorname + " " + nachname);

        return ResponseEntity.ok(mapper.convert(personService.findeAlle()));
    }


    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) throws PersonenServiceException{
       if(personService.loesche(id))
           return ResponseEntity.ok().build();
       else
           return ResponseEntity.notFound().build();
    }
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save (@Valid @RequestBody  PersonDTO personDTO,  UriComponentsBuilder uriBuilder ) throws PersonenServiceException {

        // Speichern der Person
        if(personService.speichern(mapper.convert(personDTO))) {
            UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDTO.getId());
            return ResponseEntity.created(uriComponents.toUri()).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update (@PathVariable UUID id,@Valid @RequestBody  PersonDTO personDTO ) throws PersonenServiceException{

        // Speichern der Person
        if(! id.equals(personDTO.getId())) {
            throw new IllegalArgumentException("upps");
        }
        if(personService.aendern(mapper.convert(personDTO)))
            return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }
}
