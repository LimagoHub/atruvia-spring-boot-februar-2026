package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.entity.FuetterungEntity;
import de.fi.webapp.persistence.repository.FuetterungenRepository;
import de.fi.webapp.presentation.dto.SchweinFuetternCommand;
import de.fi.webapp.service.SchweineService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SchweinFuetternListener {

    private final SchweineService schweineService;
    private final FuetterungenRepository fuetterungRepository;

    @Async
    @EventListener
    @Transactional
    public void handleFuetternEvent(SchweinFuetternCommand command) {
        // 1. Idempotenz-Check (Event-ID ist der Primary Key)
        if (fuetterungRepository.existsById(command.getEventId())) {
            return; // Bereits verarbeitet oder in Arbeit
        }

        // 2. Initialen Status speichern
        FuetterungEntity entity = FuetterungEntity.builder()
                .eventId(command.getEventId())
                .schweinId(command.getSchweinId())
                .anzahlKartoffeln(command.getAnzahlKartoffeln())
                .state("PROCESSING")
                .build();
        fuetterungRepository.save(entity);

        try {
            // 3. Logik im Service aufrufen
            schweineService.fuettern(command.getSchweinId()/*, command.getAnzahlKartoffeln()*/);

            // 4. Erfolg setzen
            entity.setState("COMPLETED");
        } catch (Exception e) {
            entity.setState("FAILED");
            // Hier ggf. Error-Logging
        }

        fuetterungRepository.save(entity);
    }
}