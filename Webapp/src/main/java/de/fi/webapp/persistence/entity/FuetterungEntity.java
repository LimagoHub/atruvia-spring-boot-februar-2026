package de.fi.webapp.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_fuetterungen")
public class FuetterungEntity {

    @Id
    private UUID eventId;
    private UUID schweinId;
    @Builder.Default
    private int anzahlKartoffeln = 1;
    @Builder.Default
    private String state = "Pending";
}
