package de.fi.webapp.service.config;


import de.fi.webapp.YamlPropertySourceFactory;
import de.fi.webapp.service.MailConfigService;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="mail")
@PropertySource(value = "classpath:mail.yaml",factory = YamlPropertySourceFactory.class)
@Setter
public class MailConfig {


    private String smtpHost;
    private String username;
    private String password;
    private String protocol;

    @Bean
    public MailConfigService createMailConfigService() {
        return new MailConfigService(smtpHost,username,password,protocol);
    }
}
