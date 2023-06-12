CREATE TABLE city (
                      city_id SERIAL PRIMARY KEY NOT NULL,
                      name VARCHAR(200) NOT NULL UNIQUE,
                      latitude FLOAT8,
                      longitude FLOAT8

);
