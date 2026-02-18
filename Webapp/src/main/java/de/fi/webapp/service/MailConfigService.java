package de.fi.webapp.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MailConfigService {

    private final String smtpHost;
    private final String username;
    private final String password;
    private final String protocol;

    public void send(String subject, String text) {
        System.out.println(String.format("Send Mail: %s mit text %s an Server %s", subject, text, smtpHost));
    }
}
