package de.fi.webapp.service.model;

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
        try {
            Thread.sleep(10000);
            setGewicht( getGewicht() + 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
