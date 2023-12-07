package ke.co.safaricom.dao;

import ke.co.safaricom.models.Animals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ke.co.safaricom.config.DatabaseConfig;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static ke.co.safaricom.constants.constants.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalsDaoTest {

    private static final Sql2o sql2o  = DatabaseConfig.getDatabaseProduction();

    private static Connection connection = sql2o.open();

    Animals animals=null;

    @BeforeEach
    static void eachSetUp(){

        Animals animals = new Animals(normal,"Impala1",healthy,young);
        String query = "INSERT INTO animals ( type ,name, health, age) VALUES (:type,:name, :health, :age);";
        connection.createQuery(query)
                .addParameter("type", animals.getType())
                .addParameter("name", animals.getName())
                .addParameter("health",animals.getHealth())
                .addParameter("age",animals.getAge())
                .executeUpdate();

    }


    @Test
    @DisplayName(value = "Create animal")
    void createAnimalTest() {

        String queryB = "SELECT * FROM animals WHERE NOT deleted AND name = :name;";
        Animals animals1 = connection.createQuery(queryB)
                .addParameter("name", animals.getName())
                .executeAndFetchFirst(Animals.class);

        assertEquals(young,animals1.getAge());
        assertNotEquals(healthy,animals1.getAge());
    }



    @Test
    @DisplayName(value = "Find animal by Id")
    void findAnimalByIdTest() {
        String queryAnimals = "SELECT * FROM animals WHERE NOT deleted AND id = :id;";
        Animals animals1 = connection.createQuery(queryAnimals)
                .addParameter("id", animals.getId())
                .executeAndFetchFirst(Animals.class);

        assertEquals("Impala1",animals1.getName());
        assertNotEquals("Query",animals1.getName());
    }


    @AfterEach
    void tearDown() {
        String query = "DELETE FROM animals WHERE id = :id;";
        connection.createQuery(query)
                .addParameter("id", animals.getId())
                .executeUpdate();
    }

}