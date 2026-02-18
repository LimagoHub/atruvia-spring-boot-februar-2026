package de.fi.webapp.presentation.controller.v2;


import de.fi.webapp.persistence.repository.FuetterungenRepository;
import de.fi.webapp.persistence.repository.SchweineRepository;
import de.fi.webapp.presentation.dto.SchweinFuetternCommand;
import de.fi.webapp.service.AlreadyExistsException;
import de.fi.webapp.service.NotFoundException;
import de.fi.webapp.service.SchweineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v2/schweine")
@RequiredArgsConstructor


public class SchweineCommandControllerV2 {

    private final ApplicationEventPublisher eventPublisher;
    private final SchweineRepository schweineRepository;
    private final FuetterungenRepository fuetterungenRepository;

    @PostMapping(path = "/{id}/fuetterungen")
    public ResponseEntity<Void> fuettern(@PathVariable UUID id, @RequestBody @Valid SchweinFuetternCommand command) {


        if(! schweineRepository.existsById(id)) throw new NotFoundException("Schwein existiert nicht");
        if( fuetterungenRepository.existsById(command.getEventId())) throw new AlreadyExistsException("Fuetterung ist bereits erfolgt");

        // Sicherstellen, dass die ID im Pfad mit der im Command übereinstimmt
        command.setSchweinId(id);

        // Event synchron veröffentlichen (das "Abfeuern" ist extrem schnell)
        eventPublisher.publishEvent(command);

        // 202 Accepted ist der richtige Status für asynchrone Prozesse
        return ResponseEntity.accepted().build();
    }
}