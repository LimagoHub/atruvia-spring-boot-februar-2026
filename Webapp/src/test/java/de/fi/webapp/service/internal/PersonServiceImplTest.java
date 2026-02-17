package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.repository.PersonRepository;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl objectUnderTest;

    @Mock
    private PersonRepository personRepositoryMock;

    @Mock
    private PersonMapper mapperMock;

    @Test
    @DisplayName("speichern mit leerem Parameter erwartet eine PersonenServiceException")
    void speichernParameterNull() throws Exception {
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Parameter darf nicht null sein", ex.getMessage());
    }

}