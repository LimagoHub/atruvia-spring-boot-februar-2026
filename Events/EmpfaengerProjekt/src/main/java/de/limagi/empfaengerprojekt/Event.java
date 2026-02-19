package de.limagi.empfaengerprojekt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private LocalDateTime timestamp = LocalDateTime.now();
    private String payload;
}
