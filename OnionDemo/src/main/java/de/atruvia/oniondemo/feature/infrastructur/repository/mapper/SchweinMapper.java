package de.atruvia.oniondemo.feature.infrastructur.repository.mapper;

import de.atruvia.oniondemo.feature.domain.Schwein;
import de.atruvia.oniondemo.feature.infrastructur.repository.entity.SchweinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {
    Schwein convert(SchweinEntity schweinEntity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> schweinEntities);
}
