package de.limago.sender;

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
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String payload;
}
