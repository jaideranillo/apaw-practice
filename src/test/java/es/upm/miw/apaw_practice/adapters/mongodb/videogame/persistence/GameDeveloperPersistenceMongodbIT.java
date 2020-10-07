package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.GameDeveloper;
import es.upm.miw.apaw_practice.domain.models.videogame.GameDeveloperCreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class GameDeveloperPersistenceMongodbIT {

    @Autowired
    private GameDeveloperPersistenceMongodb gameDeveloperPersistence;

    @Test
    void testCreate() {
        GameDeveloperCreation gameDeveloperCreation =
                new GameDeveloperCreation("Javier", "javiersanzchez@hotmail.com", 665499345);
        GameDeveloper gameDeveloper = gameDeveloperPersistence.create(gameDeveloperCreation);
        assertEquals("Javier", gameDeveloper.getName());
    }

    @Test
    void testCreateConflict() {
        GameDeveloperCreation gameDeveloperCreation =
                new GameDeveloperCreation("Pedro", "pedrofernandez@hotmail.com", 667659345);
        assertThrows(ConflictException.class, () -> this.gameDeveloperPersistence.create(gameDeveloperCreation));
    }
}