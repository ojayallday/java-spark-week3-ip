CREATE TABLE "animals" (
                           "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                           "name" varchar UNIQUE,
                           "type" varchar,
                           "health" varchar,
                           "age" varchar,
                           "deleted" boolean DEFAULT false
);

CREATE TABLE "sightings" (
                             "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             "animal_id" int,
                             "location" varchar,
                             "timestamp" varchar,
                             "ranger_name" varchar,
                             "deleted" boolean DEFAULT false
);

ALTER TABLE "sightings" ADD FOREIGN KEY ("animal_id") references "animals" ("id");


-- Sample Animals
INSERT INTO animals (name, type,health,age,deleted) VALUES ('TURTLE1','NORMAL','HEALTHY','YOUNG');
INSERT INTO animals (name, type,health,age,deleted) VALUES ('LION1','NORMAL','HEALTHY','YOUNG');
INSERT INTO animals (name, type,health,age,deleted) VALUES ('RHINO1','NORMAL','HEALTHY','YOUNG');
INSERT INTO animals (name, type,health,age,deleted) VALUES ('ANTELOPE1','NORMAL','HEALTHY','YOUNG');


--Sample Sightings

INSERT INTO sightings (location, ranger_name,animal_id) VALUES ('ZONE-A', 'Shikwekwe',1);
INSERT INTO sightings (location, ranger_name, animal_id) VALUES ('NEAR_RIVER', 'Kazi',2);
INSERT INTO sightings (location, ranger_name,animal_id) VALUES ('NE_QUADRANT', 'Lukaku',3);
INSERT INTO sightings (location, ranger_name,animal_id) VALUES ('NEAR_RIVER', 'Jackson', 4);