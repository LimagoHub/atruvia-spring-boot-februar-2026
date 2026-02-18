package de.fi.webapp.service;

import de.fi.webapp.service.model.Person;

public interface BlacklistService {

    boolean istBlacklisted(final Person possibleBlacklistedPerson);
}
