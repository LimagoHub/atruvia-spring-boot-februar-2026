package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.presentation.dto.SchweinDto;
import de.fi.webapp.presentation.mapper.SchweinDtoMapper;
import de.fi.webapp.service.SchweineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineQueryController {

    private final SchweineService schweineService;
    private final SchweinDtoMapper mapper;

    @Operation(summary = "Liefert ein Schwein")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schwein gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SchweinDto> getSchwein(UUID id) {
        return ResponseEntity.of(schweineService.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SchweinDto>> getSchweine() {
        return ResponseEntity.ok(mapper.convert(schweineService.findeAlle()));
    }


}
