package de.atruvia.oniondemo.feature.infrastructur.config;


import de.atruvia.oniondemo.feature.application.SchweinRepositoryPort;
import de.atruvia.oniondemo.feature.application.SchweineService;
import de.atruvia.oniondemo.feature.application.internal.SchweineServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchweinServiceConfig {


    @Bean
    public SchweineService createSchweineService(final SchweinRepositoryPort schweinRepositoryPort) {
        return new SchweineServiceImpl(schweinRepositoryPort);
    }
}
