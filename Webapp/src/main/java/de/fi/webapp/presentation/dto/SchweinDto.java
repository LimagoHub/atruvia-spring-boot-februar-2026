package de.fi.webapp.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDto {

    private UUID id;
    private String name;

    @DecimalMin(value = "10")
    private int gewicht;
}
