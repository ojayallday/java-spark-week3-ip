package ke.co.safaricom.dao;

import ke.co.safaricom.models.Sightings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ke.co.safaricom.config.DatabaseConfig;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static ke.co.safaricom.constants.constants.zoneA;
import static org.junit.jupiter.api.Assertions.*;

class SightingsDaoTest {

    private static final Sql2o sql2o  = DatabaseConfig.getDatabaseProduction();

    private static Connection connection = sql2o.open();

    Sightings sightings=null;

    @BeforeEach
    void eachSetUp(){

        sightings  = new Sightings(1,zoneA ,"kitty");

        String query = "INSERT INTO sightings ( animal_id ,location, ranger_name) VALUES (:animalId,:location, :rangerName);";
        connection.createQuery(query)
                .addParameter("animalId", sightings.getAnimal_id())
                .addParameter("location", sightings.getLocation())
                .addParameter("rangerName",sightings.getRangerName())
                .executeUpdate();

    }

    @Test
    @DisplayName(value = "Create sighting")
    void  createSighting() {
        String queryB = "SELECT * FROM sightings WHERE NOT deleted AND animal_id = :id;";
        Sightings sightings1 = connection.createQuery(queryB)
                .addParameter("id", sightings.getId())
                .executeAndFetchFirst(Sightings.class);

        assertEquals(zoneA,sightings1.getLocation());
        assertNotEquals("healthy",sightings1.getLocation());

    }
    @AfterEach
    void tearDown() {
        String query = "DELETE FROM sightings WHERE animal_id = :id;";
        connection.createQuery(query)
                .addParameter("id", sightings.getId())
                .executeUpdate();
    }

}