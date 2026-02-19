package de.atruvia.oniondemo.feature.application.internal;

import de.atruvia.oniondemo.feature.application.SchweinRepositoryPort;
import de.atruvia.oniondemo.feature.application.SchweineService;
import de.atruvia.oniondemo.feature.application.SchweineServiceException;
import de.atruvia.oniondemo.feature.domain.Schwein;
import lombok.RequiredArgsConstructor;


import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {

    private final SchweinRepositoryPort schweinRepositoryPort;

    @Override
    public void speichern(final Schwein schwein) {
        if(schwein == null) throw new SchweineServiceException("Schwein darf nicht null sein");
        if(schwein.getId() == null) throw new SchweineServiceException("Schwein ID darf nicht null sein");
        schweinRepositoryPort.persist(schwein);
    }

    @Override
    public void aendern(final Schwein schwein) {
        schweinRepositoryPort.update(schwein);
    }

    @Override
    public Optional<Schwein> findeNachId(final UUID id) {
        return schweinRepositoryPort.findById(id);
    }

    @Override
    public Iterable<Schwein> findeAlle() {
        return schweinRepositoryPort.findAll();
    }

    @Override
    public void loesche(final UUID id) {
        schweinRepositoryPort.delete(id);
    }

    /*@Override
    public void fuettern(final UUID id) {
        var schwein = findeNachId(id).orElseThrow(() -> new NotFoundException("Schwein existiert nicht"));
        schwein.fuettern();
        aendern(schwein);
    }*/

    /*

       Validator.of(schwein)

        // 1. Technische & Fachliche Validierung (Fehler werden gesammelt)

        .validate(Objects::nonNull, "Das Schwein darf nicht null sein")

        .validate(s -> s.getId() != null, "ID darf nicht null sein")

        .validate(s -> s.getName() != null, "Name darf nicht null sein")

        .validate(s -> s.getName().length() >= 2 && s.getName().length() <= 30, "Name muss 2-30 Zeichen lang sein")

        .validate(s -> s.getGewicht() >= 10, "Ein Schwein muss mindestens 10kg wiegen")

        .validate(s -> !"Elsa".equalsIgnoreCase(s.getName()), "Name 'Elsa' verletzt die Schweinewürde")

        .throwIfInvalid(SchweineServiceException::new)



        // 2. Zustandsprüfung (Spezifischer Abbruch)

        .checkOrThrow(

            s -> !schweinRepository.existsById(s.getId()),

            s -> new AlreadyExistsException("Schwein existiert bereits: " + s.getId())

        );



    persist(schwein);
     */
}
