package ke.co.safaricom;

import ke.co.safaricom.dao.AnimalsDao;
import ke.co.safaricom.dao.SightingsDao;
import ke.co.safaricom.models.Animals;
import ke.co.safaricom.models.Sightings;
import ke.co.safaricom.utils.SharedUtils;

import static spark.Spark.*;

import java.util.HashMap;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "templates/index.hbs");
        });
        get("/wildlife/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "templates/wildlife-details.hbs");
        });
        get("/animal/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "templates/animal-details.hbs");
        });

        post("/create-sighting", (req, res)->{
            String id = req.queryParams("id");
            String location =req.queryParams("location");
            String rangerName =req.queryParams("rangername");

            try{
                if( id ==null  || id.length()==0
                        || location==null || location.length()==0 || rangerName==null || rangerName.length()==0 ){
                    throw new IllegalArgumentException("invalid input all fields have to be provided");
                }
                int latestId= Integer.valueOf(id);
                Animals animal=AnimalsDao.findAnimalById(latestId);
                Sightings sightings= new Sightings(animal.getId(), location, rangerName);

                //save sighting
                SightingsDao.createSighting(sightings);

            }catch (Exception e){

                System.out.print(e.getMessage());
            }

            res.redirect("/");
            return null;
        });

        post("/create-animal", (req, res)->{
            String category = req.queryParams("category");
            String name = req.queryParams("name");
            String health= req.queryParams("health");
            String age=req.queryParams("age");

            try{
                if(category==null || category.length()==0 || name==null|| name.length()==0|| health==null ||health.length()==0 || age ==null || age.length()==0){
                    System.out.print(category);
                    System.out.print(name);
                    System.out.print(health);
                    System.out.print(age);
                    throw new IllegalArgumentException("invalid input all fields have to be provided");
                }

                Animals animals= new Animals(category,name,health,age);
                AnimalsDao.createAnimal(animals);

            }catch (Exception e){

                System.out.print(e.getMessage());
            }

            res.redirect("/");
            return null;
        });
    }
}