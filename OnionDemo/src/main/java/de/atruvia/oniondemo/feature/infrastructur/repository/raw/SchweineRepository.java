package de.atruvia.oniondemo.feature.infrastructur.repository.raw;

import de.atruvia.oniondemo.feature.infrastructur.repository.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweineRepository extends CrudRepository<SchweinEntity, UUID> {
}
