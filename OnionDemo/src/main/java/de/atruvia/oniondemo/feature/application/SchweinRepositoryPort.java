package de.atruvia.oniondemo.feature.application;

import de.atruvia.oniondemo.feature.domain.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweinRepositoryPort {

    void persist(Schwein schwein);
    void update(Schwein schwein);
    void delete(UUID id);
    Optional<Schwein> findById(UUID id);
    Iterable<Schwein> findAll();
}
