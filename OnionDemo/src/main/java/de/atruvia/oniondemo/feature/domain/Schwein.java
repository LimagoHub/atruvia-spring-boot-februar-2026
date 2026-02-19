package de.atruvia.oniondemo.feature.domain;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Schwein {

    private UUID id;
    private String name;
    private int gewicht;

    public void taufen(String name) {
        if("Elsa".equals(name)) throw new IllegalArgumentException("Name verstoesst gegen die Schweinewuerde!");
    }

    public void fuettern() {
            setGewicht( getGewicht() + 1);
    }
}
