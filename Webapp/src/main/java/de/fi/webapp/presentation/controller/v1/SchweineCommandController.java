package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.SchweinDto;
import de.fi.webapp.presentation.errorhandler.IdMismatchException;
import de.fi.webapp.presentation.mapper.SchweinDtoMapper;
import de.fi.webapp.service.SchweineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineCommandController {

    private final SchweineService schweineService;
    private final SchweinDtoMapper mapper;

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> deleteSchwein(@PathVariable UUID id)  {
        schweineService.loesche(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save (@Valid @RequestBody SchweinDto schweinDto, UriComponentsBuilder uriBuilder ) {


        schweineService.speichern(mapper.convert(schweinDto));
        UriComponents uriComponents = uriBuilder.path("/v1/schweine/{id}").buildAndExpand(schweinDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();


    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update (@PathVariable UUID id,@Valid @RequestBody SchweinDto schweinDto ) {

        // Speichern der Person
        if(! id.equals(schweinDto.getId())) {
            throw new IdMismatchException("The ID in the URL path does not match the ID in the request body.");
        }
        schweineService.aendern(mapper.convert(schweinDto));
        return ResponseEntity.noContent().build();

    }

    @PostMapping(path = "/{id}/fuetterungen")
    public ResponseEntity<Void> fuettern (@PathVariable UUID id, UriComponentsBuilder uriBuilder ) {
        schweineService.fuettern(id);
        UriComponents uriComponents = uriBuilder.path("/v1/schweine/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
