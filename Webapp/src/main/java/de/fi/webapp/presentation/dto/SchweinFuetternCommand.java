package de.fi.webapp.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinFuetternCommand {

    private UUID eventId;
    private UUID schweinId;
    @Builder.Default
    private int anzahlKartoffeln = 1;
    @Builder.Default
    private String state = "Pending";
}
