package ke.co.safaricom.config;

import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {

    public static Sql2o getDatabaseProduction(){
        //database url and password
        return new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "postgres", "ojayallday");

    }



}