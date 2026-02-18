package de.fi.webapp.persistence.repository;

import de.fi.webapp.persistence.entity.FuetterungEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FuetterungenRepository extends CrudRepository<FuetterungEntity, UUID> {
}
