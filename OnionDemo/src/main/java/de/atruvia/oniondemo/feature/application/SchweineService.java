package de.atruvia.oniondemo.feature.application;


import de.atruvia.oniondemo.feature.domain.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein schwein) ;
    void aendern(Schwein schwein) ;
    Optional<Schwein> findeNachId(UUID id) ;
    Iterable<Schwein> findeAlle() ;
    void loesche(UUID id) ;
    //void fuettern(UUID id) ;
}
