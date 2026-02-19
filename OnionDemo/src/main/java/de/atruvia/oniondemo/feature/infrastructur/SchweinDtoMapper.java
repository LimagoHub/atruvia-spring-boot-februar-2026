package de.atruvia.oniondemo.feature.infrastructur;


import de.atruvia.oniondemo.feature.domain.Schwein;
import de.atruvia.oniondemo.feature.infrastructur.dto.SchweinDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {
    SchweinDto convert(Schwein schwein);
    Schwein convert(SchweinDto schweinDto);
    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
