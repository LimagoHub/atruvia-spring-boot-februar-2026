package de.atruvia.oniondemo.feature.infrastructur;

import de.atruvia.oniondemo.feature.application.SchweineService;
import de.atruvia.oniondemo.feature.domain.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SchweineHandler {

    private final SchweineService schweineService;

    public void handleSpeichern(final Schwein schwein) {
        schweineService.speichern(schwein);
        // feuern mit succesevent
    }

    public void handleAendern(final Schwein schwein) {
    }
}
