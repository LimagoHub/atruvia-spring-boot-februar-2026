package de.fi.webapp.service;


import de.fi.webapp.service.model.Person;
import de.fi.webapp.service.model.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein schwein) ;
    void aendern(Schwein schwein) ;
    Optional<Schwein> findeNachId(UUID id) ;
    Iterable<Schwein> findeAlle() ;
    void loesche(UUID id) ;
    void fuettern(UUID id) ;
}
