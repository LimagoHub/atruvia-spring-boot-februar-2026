package de.atruvia.oniondemo.feature.infrastructur.controller;

import de.atruvia.oniondemo.feature.domain.Schwein;
import de.atruvia.oniondemo.feature.infrastructur.SchweinDtoMapper;
import de.atruvia.oniondemo.feature.infrastructur.SchweineHandler;
import de.atruvia.oniondemo.feature.infrastructur.dto.SchweinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineController {

    private final SchweineHandler schweineHandler;
    private final SchweinDtoMapper schweinDtoMapper;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> speichern(@RequestBody final SchweinDto schwein) {
        schweineHandler.handleSpeichern(schweinDtoMapper.convert(schwein));
        return ResponseEntity.ok().build();
    }
}
