package de.atruvia.oniondemo.feature.infrastructur.repository;

import de.atruvia.oniondemo.feature.application.SchweinRepositoryPort;
import de.atruvia.oniondemo.feature.domain.Schwein;
import de.atruvia.oniondemo.feature.infrastructur.AlreadyExistsException;
import de.atruvia.oniondemo.feature.infrastructur.NotFoundException;
import de.atruvia.oniondemo.feature.infrastructur.repository.mapper.SchweinMapper;
import de.atruvia.oniondemo.feature.infrastructur.repository.raw.SchweineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SchweinRepositoryPortImpl implements SchweinRepositoryPort {

    private final SchweineRepository schweinRepository;
    private final SchweinMapper schweinMapper;
    @Override
    public void persist(final Schwein schwein) {
        if(schweinRepository.existsById(schwein.getId())) throw new AlreadyExistsException("Schwein existiert bereits");
        schweinRepository.save(schweinMapper.convert(schwein));
    }

    @Override
    public void update(final Schwein schwein) {
        if(! schweinRepository.existsById(schwein.getId())) throw new NotFoundException("Nix Schwein!");
        schweinRepository.save(schweinMapper.convert(schwein));
    }

    @Override
    public void delete(final UUID id) {

    }

    @Override
    public Optional<Schwein> findById(final UUID id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Schwein> findAll() {
        return null;
    }

    @SpringBootApplication
    public static class OnionDemoApplication {

        public static void main(String[] args) {
            SpringApplication.run(OnionDemoApplication.class, args);
        }

    }
}
